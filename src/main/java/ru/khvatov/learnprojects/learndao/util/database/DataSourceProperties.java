package ru.khvatov.learnprojects.learndao.util.database;

import ru.khvatov.learnprojects.learndao.util.database.exeption.NoSuchPropertyException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DataSourceProperties {
    private static final String PROPERTIES_FILE = "database-connection.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream resourceStream = DataSourceProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);) {
            PROPERTIES.load(resourceStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private DataSourceProperties() {
    }

    public enum PropertyKey {
        URL("database.url"),
        SCHEMA("database.schema"),
        USERNAME("database.username"),
        PASSWORD("database.password"),

        CONNECTION_POOL_SIZE("database.pool.size");

        private final String label;

        PropertyKey(String label) {
            this.label = label;
        }
    }

    public static String get(PropertyKey key) throws NoSuchPropertyException {
        if (PROPERTIES.containsKey(key.label)) {
            return PROPERTIES.getProperty(key.label);
        }

        String message = "Properties file `%s` doesn't contains the key `%s`. Perhaps, you forget to add it."
                .formatted(PROPERTIES_FILE, key.label);
        throw new NoSuchPropertyException(message);
    }
}
