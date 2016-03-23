package com.epam.alexandrli.paintballshop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    public PropertyManager() {
    }

    public static Properties getProperties(String fileName) throws PropertyManagerException {
        Properties properties = new Properties();
        try (InputStream in = PropertyManager.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(in);
        } catch (IOException e) {
            throw new PropertyManagerException("Couldn't load property file", e);
        }
        return properties;
    }

}
