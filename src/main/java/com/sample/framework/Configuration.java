package com.sample.framework;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/12/18
 * Time: 4:02 PM
 */
public class Configuration {
    private Configuration() {

    }

    private static Properties properties;

    public static void load() throws IOException {
        Configuration config = new Configuration();
        properties = new Properties();
        InputStream is = new FileInputStream(new File("test-framework.properties"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        try {
            properties.load(reader);
        } finally {
            is.close();
            reader.close();
        }
    }

    public static String get(String option)  {
        if (properties == null) {
            try {
                load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String value = properties.getProperty(option);
        if (value == null) {
            return "";
        }
        return value;
    }

    public static long timeout()  {
        String value = get("timeout");
        if (value == null || value.trim().equals("")) {
            return 60L;
        }
        return Long.parseLong(value);
    }
}
