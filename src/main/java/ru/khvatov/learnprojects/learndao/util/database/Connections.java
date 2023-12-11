package ru.khvatov.learnprojects.learndao.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ru.khvatov.learnprojects.learndao.util.database.DataSourceProperties.PropertyKey.PASSWORD;
import static ru.khvatov.learnprojects.learndao.util.database.DataSourceProperties.PropertyKey.SCHEMA;
import static ru.khvatov.learnprojects.learndao.util.database.DataSourceProperties.PropertyKey.URL;
import static ru.khvatov.learnprojects.learndao.util.database.DataSourceProperties.PropertyKey.USERNAME;

public final class Connections {

    static {
        loadDriverClass();
    }

    private static void loadDriverClass() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Connections() {
    }

    public static Connection open() {
        try {
            Connection connection = DriverManager.getConnection(
                    DataSourceProperties.get(URL),
                    DataSourceProperties.get(USERNAME),
                    DataSourceProperties.get(PASSWORD)
            );

            connection.setSchema(
                    DataSourceProperties.get(SCHEMA)
            );

            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
