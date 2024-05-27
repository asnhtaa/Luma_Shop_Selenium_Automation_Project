package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        try {
            InputStream input = new FileInputStream(".env-test");
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Error: Unable to load configuration file .env-test");
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return properties.getProperty("user_email");
    }

    public String getPassword() {
        return properties.getProperty("user_password");
    }
}
