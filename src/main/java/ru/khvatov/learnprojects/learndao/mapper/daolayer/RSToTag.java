package ru.khvatov.learnprojects.learndao.mapper.daolayer;

import ru.khvatov.learnprojects.learndao.entity.Tag;
import ru.khvatov.learnprojects.learndao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RSToTag implements Mapper<ResultSet, Tag> {
    @Override
    public Tag map(ResultSet obj) {
        try {
            return new Tag(
                    obj.getLong("id"),
                    obj.getLong("account_id"),
                    obj.getString("name")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
