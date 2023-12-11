package ru.khvatov.learnprojects.learndao.mapper.daolayer;

import ru.khvatov.learnprojects.learndao.entity.Account;
import ru.khvatov.learnprojects.learndao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RSToAccountMapper implements Mapper<ResultSet, Account> {

    @Override
    public Account map(ResultSet resultSet) {
        try {
            return new Account(resultSet.getLong("id"), resultSet.getString("email"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
