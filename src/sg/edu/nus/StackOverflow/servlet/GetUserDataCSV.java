package sg.edu.nus.StackOverflow.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.StackOverflow.model.User;
import sg.edu.nus.StackOverflow.util.DB;

/**
 * Servlet to output all of the user data, stored in the database, as CSV file. 
 * It does not use any parameters. 
 */
@WebServlet("/user_data_csv")
public class GetUserDataCSV extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = DB.getAllUsers();
        long now = new Date().getTime() / 1000;

        response.setContentType("text/csv");

        PrintWriter writer = response.getWriter();
        writer.print("Reputation,");
        writer.print("Account_Age,");//in months
        writer.print("User_Age,");
        writer.print("Link,");
        writer.print("Country\n");

        for (User user : users) {
            writer.print(user.getReputation() + ",");
            writer.print(((now - user.getCreation_date()) / 2628000) + ",");
            writer.print(user.getAge() + ",");
            writer.print("\"" + user.getLink() + "\",");
            writer.print("\"" + user.getCountry() + "\"\n");
        }
        writer.close();
    }

}
