package sg.edu.nus.StackOverflow.util;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sg.edu.nus.StackOverflow.model.User;

public enum StackOverflowParser {
    INSTANCE;

    public static User parseUser(User user) throws IOException {

        Document doc = Net.getDoc(user.getLink());

        String location = "";
        int age = 0;
        int reputation = 0;

        Elements trs = doc.select("div.data tr");
        for (Element tr : trs) {
            Elements tds = tr.select("td");
            if (tds.size() == 2) {
                if (tds.get(0).text().equalsIgnoreCase("age")) {
                    String text = tds.get(1).text();
                    if (text.length() > 0) {
                        age = Integer.parseInt(text);
                    }
                }
                if (tds.get(0).text().equalsIgnoreCase("location")) {
                    location = tds.get(1).text();
                }
            }
        }
        reputation = Integer.parseInt(doc.select("div.reputation span.reputation-score")
                .text().replaceAll("[^\\d]", ""));
        user.setParsedLocation(location);
        user.setParsedReputation(reputation);
        user.setParsedAge(age);

        return user;
    }
}
