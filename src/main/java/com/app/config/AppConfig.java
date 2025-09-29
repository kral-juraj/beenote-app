package com.app.config;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application configuration manager
 * Handles loading and accessing application configuration properties
 */
public class AppConfig {
    private static final Logger logger = Logger.getLogger(AppConfig.class.getName());
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "/config.properties";
    private static final String DB_URL_KEY = "db.url";

    // Database configuration
    private static final String DB_PATH = "src/main/resources/bee_note.db";
    private static final String DB_DEFAULT_URL = "jdbc:sqlite:" + new File(DB_PATH).getAbsolutePath();

    // Additional configuration keys
    private static final String APP_NAME_KEY = "app.name";
    private static final String APP_VERSION_KEY = "app.version";

    static {
        try {
            initialize();
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "Failed to initialize application configuration", e);
            throw e;
        }
    }

    private static void initialize() {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = AppConfig.class.getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                properties.load(input);
                logger.info("Successfully loaded configuration from " + CONFIG_FILE);
            } else {
                logger.warning("Configuration file not found. Using default values.");
                setDefaultProperties();
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to load configuration file. Using default values.", e);
            setDefaultProperties();
        }
    }

    /**
     * Sets default configuration properties
     */
    private static void setDefaultProperties() {
        properties.setProperty(DB_URL_KEY, DB_DEFAULT_URL);
        properties.setProperty(APP_NAME_KEY, "BeeNote");
        properties.setProperty(APP_VERSION_KEY, "1.0.0");

        logger.info("Default properties have been set");
    }

    /**
     * Gets the database URL from configuration
     *
     * @return the configured database URL or default SQLite URL
     */
    public static String getDatabaseUrl() {
        return properties.getProperty(DB_URL_KEY, DB_DEFAULT_URL);
    }

    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        if (value == null && defaultValue == null) {
            logger.warning("No value found for key: " + key + " and no default value provided");
        }
        return value;
    }
}
