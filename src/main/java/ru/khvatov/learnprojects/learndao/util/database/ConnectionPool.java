package ru.khvatov.learnprojects.learndao.util.database;

import java.sql.Connection;

public interface ConnectionPool extends AutoCloseable {

    Connection getConnection();

}
