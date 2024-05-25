package com.mayer.properties.java;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertiesChecker {

    public static void main(String[] args) throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "app.properties";

        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));

        String appVersion = appProps.getProperty("version");
        assertEquals("1.0", appVersion);

        String newAppConfigPropertiesFile = rootPath + "newApp.properties";
        appProps.setProperty("TestKey", "TestValue");
        System.setProperties(appProps);
        appProps.store(new FileWriter(newAppConfigPropertiesFile), "store to properties file");

        String newAppConfigPath = rootPath + "newApp.properties";
        Properties newAppProps = new Properties();
        newAppProps.load(new FileInputStream(newAppConfigPath));
        String testKey = newAppProps.getProperty("TestKey");
        assertEquals("TestValue", testKey);
    }

}