package sg.edu.nus.StackOverflow.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.StackOverflow.model.User;
import sg.edu.nus.StackOverflow.util.DB;
import sg.edu.nus.StackOverflow.util.StackOverflowAPI;

/**
 * Servlet implementation class GetUsers
 */
@WebServlet("/get_users")
public class GetUsers extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Servlet to get user profiles via StackOverflow API and save them in MongoDB database. 
     * The process is done in batches of size 'pagesize'. 
     * Sample URL query: /get_users?pagesize=100&min=5000&max=10000
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagesizeString = request.getParameter("pagesize");
        String minString = request.getParameter("min");
        String maxString = request.getParameter("max");
        Integer pageSize = null;
        Integer minRating = null;
        Integer maxRating = null;

        if (pagesizeString != null) {
            pageSize = Integer.parseInt(pagesizeString);
        }

        if (minString != null) {
            minRating = Integer.parseInt(minString);
        }

        if (maxString != null) {
            maxRating = Integer.parseInt(maxString);
        }

        for (int ind = maxRating; ind >= minRating; ind -= pageSize) {
            User[] users = StackOverflowAPI.getUsers(pageSize, minRating, ind);
            DB.saveModels(users);
        }
        response.setContentType("text/plain");
        response.getWriter().append("Done.");
    }
}
