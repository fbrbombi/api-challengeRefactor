package helpers;

import java.io.IOException;
import java.util.Properties;


class ConfigLoader {
    private static final String PROPERTIES_FILENAME = "/config.properties";
    private static Properties properties;

    private ConfigLoader() {
    }

    public static String getValueByKey(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.get(key).toString();
    }

    private static void loadProperties() {
        try {
            properties = new Properties();
            properties.load(ConfigLoader.class.getResourceAsStream(PROPERTIES_FILENAME));
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
    }

}


