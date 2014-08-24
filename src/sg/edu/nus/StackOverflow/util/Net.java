package sg.edu.nus.StackOverflow.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public enum Net {
    INSTANCE;

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
