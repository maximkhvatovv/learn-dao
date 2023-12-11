package ru.khvatov.learnprojects.learndao.dao.tag.impl;

import ru.khvatov.learnprojects.learndao.dao.tag.TagDao;
import ru.khvatov.learnprojects.learndao.validator.UpdateTagValidator;
import ru.khvatov.learnprojects.learndao.validator.Validator;
import ru.khvatov.learnprojects.learndao.entity.Tag;
import ru.khvatov.learnprojects.learndao.mapper.Mapper;
import ru.khvatov.learnprojects.learndao.mapper.daolayer.RSToTag;
import ru.khvatov.learnprojects.learndao.util.database.ConnectionPool;
import ru.khvatov.learnprojects.learndao.util.database.SimpleConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleTagDao implements TagDao<Long, Tag> {

    private static final SimpleTagDao INSTANCE = new SimpleTagDao();
    private static final String FIND_ALL_QUERY = """
            SELECT id,
                   account_id,
                   name
            FROM tag
            """;
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY + """
             WHERE id = ?
            """;
    private static final String INSERT_QUERY = """
            INSERT INTO tag(account_id, name)
            VALUES(?, ?)
            """;
    private static final String UPDATE_QUERY = """
            UPDATE tag
            SET account_id = ?, name = ?
            WHERE id = ?
            """;
    private static final String DELETE_QUERY = """
            DELETE FROM tag WHERE id = ? 
            """;
    private static final String FIND_ALL_BY_ACCOUNT_ID_QUERY = """
            SELECT id, account_id, name
            FROM tag
            WHERE account_id = ?
            """;
    private final ConnectionPool connectionPool = SimpleConnectionPool.getInstance();
    private final Mapper<ResultSet, Tag> toTagMapper = new RSToTag();
    private final Validator<Tag> updateTagValidator = new UpdateTagValidator();

    private SimpleTagDao() {
    }

    public static SimpleTagDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Tag> findById(Long id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            statement.setObject(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(toTagMapper.map(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tag> findAll() {
        throw new UnsupportedOperationException(
                """
                        The method is not implemented because it can be too expensive to obtain all tags from database.
                        """
        );
    }

    @Override
    public Tag save(Tag entity) {
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setObject(1, entity.getAccountId());
            statement.setObject(2, entity.getName());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            long id = generatedKeys.getLong("id");
            entity.setId(id);

            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Tag entity) {
        updateTagValidator.validate(entity);
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(UPDATE_QUERY);
        ) {
            statement.setLong(1, entity.getAccountId());
            statement.setString(2, entity.getName());
            statement.setLong(3, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(DELETE_QUERY);
        ) {
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Tag> findAllByAccountId(Long accountId) {
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(FIND_ALL_BY_ACCOUNT_ID_QUERY);
        ) {
            statement.setLong(1, accountId);

            ResultSet resultSet = statement.executeQuery();
            List<Tag> tags = new ArrayList<>();
            while (resultSet.next()) {
                tags.add(toTagMapper.map(resultSet));
            }
            return tags;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
