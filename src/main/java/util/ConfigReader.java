package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties;
    private final String configFileName = "config.properties";

    public ConfigReader() {
        properties = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(configFileName)) {
            properties.load(in);
        } catch (IOException e) {
            System.out.println("Configuration file not found.");
        }
    }

    public String getEndpoint() {
        return properties.getProperty("endpoint");
    }

    public String getAPIKey() {
        return properties.getProperty("api_key");
    }

    public String getCity() {
        return properties.getProperty("city");
    }
}
