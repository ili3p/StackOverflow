package sg.edu.nus.StackOverflow.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.StackOverflow.model.User;
import sg.edu.nus.StackOverflow.util.DB;
import sg.edu.nus.StackOverflow.util.StackOverflowParser;

/**
 * Servlet implementation class GetParsedUsers
 */
@WebServlet("/get_parsed")
public class GetParsedUsers extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = DB.getAllUsers();
        for (User user : users) {
            User parsedUser = StackOverflowParser.parseUser(user);
            DB.updateModel(parsedUser);
            System.out.println("done with " + parsedUser.getAccount_id());
        }
    }

}
