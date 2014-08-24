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
import sg.edu.nus.StackOverflow.util.GeocodingAPI;

/**
 * Servlet to get the country codes from Google's Geocoding API
 * It does not use any parameters. 
 * The results are saved in the database.
 */
@WebServlet("/get_countries")
public class GetCountries extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = DB.getAllUsers();
        for (User user : users) {
            String location = user.getLocation();
            if (location != null && location.trim().length() > 0) {
                String country = GeocodingAPI.getCountry(location);
                user.setCountry(country);
                DB.updateModel(user);
                System.out.println("done with " + user.getAccount_id());
            }
        }
    }

}
