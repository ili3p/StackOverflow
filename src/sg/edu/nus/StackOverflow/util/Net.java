package sg.edu.nus.StackOverflow.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public enum Net {
    INSTANCE;

    /**
     * User agent string passed when using jsoup to look as normal user request.
     */
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.36";

    /**
     * Fetches jsoup document from URL. 
     * 
     * @param url, the URL containing the HTML document
     * @return the HTML doc. as jsoup doc. 
     * @throws IOException
     *             if the URL is not found or other error
     */
    public static Document getDoc(String url) throws IOException {

        try {
            return Jsoup.connect(url).timeout(10000).userAgent(USER_AGENT).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Read string from remote GZIP-ed stream using UTF-8 encoding. 
     * 
     * @param urlString, URL of the input stream
     * @return 
     *
     * @throws IOException
     */
    public static String readURL(String urlString) throws IOException {
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();

        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(
                    url.openStream()), "UTF-8"));
            char[] chars = new char[1024];
            int read;
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
        } finally {
            if (reader != null)
                reader.close();
        }

        return buffer.toString();
    }
}
