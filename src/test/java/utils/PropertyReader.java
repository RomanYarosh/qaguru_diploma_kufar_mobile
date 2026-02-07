package utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream is = PropertyReader.class.getClassLoader().getResourceAsStream("testdata.properties")) {
            properties.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить testdata.properties");
        }
    }

    public static String getProperty(String key) {
        String value = System.getProperty(key);
        if (value == null || value.isEmpty()) {
            value = properties.getProperty(key);
        }
        return value;
    }
}