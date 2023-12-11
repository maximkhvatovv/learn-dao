package ru.khvatov.learnprojects.learndao.mapper.daolayer;

import ru.khvatov.learnprojects.learndao.entity.Product;
import ru.khvatov.learnprojects.learndao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RSToProduct implements Mapper<ResultSet, Product> {
    @Override
    public Product map(ResultSet rs) {
        try {
            return new Product(
                    rs.getLong("id"),
                    rs.getLong("account_id"),
                    rs.getLong("tag_id"),
                    rs.getString("name"),
                    rs.getInt("available_count"),
                    rs.getInt("comfort_count")

            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
