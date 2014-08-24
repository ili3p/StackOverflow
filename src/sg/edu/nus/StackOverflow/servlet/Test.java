package sg.edu.nus.StackOverflow.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.StackOverflow.model.User;
import sg.edu.nus.StackOverflow.util.DB;
import sg.edu.nus.StackOverflow.util.GeocodingAPI;
import sg.edu.nus.StackOverflow.util.StackOverflowParser;

/**
 * Servlet implementation class Test
 */
@WebServlet(
        description = "Test servlet to test that the web server is up and that the MongoDB connection is working.",
        urlPatterns = { "/test" })
public class Test extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().println("Server is OK!");
        response.getWriter().println(DB.test());

        User user = DB.getUser(294226);
        user = StackOverflowParser.parseUser(user);
        System.out.println(user.getParsedAge());
        System.out.println(user.getParsedLocation());
        System.out.println(user.getParsedReputation());

        String country = GeocodingAPI.getCountry(user.getLocation());
        user.setCountry(country);
        DB.updateModel(user);
    }

}
