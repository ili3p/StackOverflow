package sg.edu.nus.StackOverflow.util;

import java.io.IOException;

import sg.edu.nus.StackOverflow.model.User;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public enum StackOverflowAPI {

    INSTANCE;

    /**
     * The API URL.
     */
    private static final String API_URL = "http://api.stackexchange.com/2.2/users?order=desc&sort=reputation&site=stackoverflow";

    /**
     * The default and the maximum number of records requested. 
     */
    private static final int PAGE_SIZE = 100;

    public static User[] getUsers(Integer pageSize, Integer minRating, Integer maxRating) {
        User[] users = null;
        try {
            JsonParser parser = new JsonParser();
            Gson gson = new Gson();

            String urlString = buildUrl(pageSize, minRating, maxRating);
            JsonElement obj = parser.parse(Net.readURL(urlString, true));
            JsonElement json = obj.getAsJsonObject().get("items");

            users = gson.fromJson(json, User[].class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    private static String buildUrl(Integer pageSize, Integer minRating, Integer maxRating) {
        StringBuilder urlBuilder = new StringBuilder(API_URL);
        int size = PAGE_SIZE;

        if (pageSize != null) {
            size = Math.max(PAGE_SIZE, pageSize);
        }
        urlBuilder.append("&pagesize=" + size);

        if (minRating != null) {
            urlBuilder.append("&min=" + minRating);
        }
        if (maxRating != null) {
            urlBuilder.append("&max=" + maxRating);
        }
        return urlBuilder.toString();
    }
}
