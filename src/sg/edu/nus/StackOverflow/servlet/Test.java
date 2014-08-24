package sg.edu.nus.StackOverflow.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.StackOverflow.util.DB;

/**
 * Servlet used for testing that the web server is up and that the MongoDB connection is working.
 */
@WebServlet("/test")
public class Test extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        writer.println("Server is OK!");
        writer.println(DB.test());
        writer.close();

    }
}
