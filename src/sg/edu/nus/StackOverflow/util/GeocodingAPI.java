package sg.edu.nus.StackOverflow.util;

import java.io.IOException;
import java.net.URLEncoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public enum GeocodingAPI {

    INSTANCE;

    private static final String API_KEY = "AIzaSyD7BOmtGa7Fjibc_IWbJXyV4HIbeKIGFoI";
    private static final String API_URL = "https://maps.googleapis.com/maps/api/geocode/json?key="
            + API_KEY;

    public static String getCountry(String location) {

        String country = "";
        try {
            String url = API_URL + "&address=" + URLEncoder.encode(location, "UTF-8");
            String jsonString = Net.readURL(url, false);

            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(jsonString).getAsJsonObject();
            JsonArray results = json.get("results").getAsJsonArray();
            JsonArray arr = results.get(0).getAsJsonObject().get("address_components").getAsJsonArray();
            for (JsonElement el : arr) {
                JsonObject obj = el.getAsJsonObject();
                JsonArray types = obj.get("types").getAsJsonArray();

                boolean isCountry = false;

                for (JsonElement type : types) {
                    if (type.getAsString().equalsIgnoreCase("country")) {
                        isCountry = true;
                        break;
                    }
                }

                if (isCountry) {
                    country = obj.get("short_name").getAsString();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return country;
    }
}
