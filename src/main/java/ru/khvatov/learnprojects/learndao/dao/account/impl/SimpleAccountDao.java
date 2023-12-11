package ru.khvatov.learnprojects.learndao.dao.account.impl;

import ru.khvatov.learnprojects.learndao.dao.account.AccountDao;
import ru.khvatov.learnprojects.learndao.validator.UpdateAccountValidator;
import ru.khvatov.learnprojects.learndao.validator.Validator;
import ru.khvatov.learnprojects.learndao.entity.Account;
import ru.khvatov.learnprojects.learndao.mapper.Mapper;
import ru.khvatov.learnprojects.learndao.mapper.daolayer.RSToAccountMapper;
import ru.khvatov.learnprojects.learndao.util.database.SimpleConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleAccountDao implements AccountDao<Long, Account> {
    private static final SimpleAccountDao INSTANCE = new SimpleAccountDao();
    private static final String FIND_ALL_QUERY = """
            SELECT id,
                   email
            FROM account
            """;
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY +
            """
                      WHERE id = ?
                    """;

    private static final String INSERT_QUERY = """
            INSERT INTO account(email)
            VALUES (?)
            """;
    private static final String UPDATE_QUERY = """
             UPDATE account
             SET email = ?
             WHERE id = ?
            """;
    private static final String DELETE_QUERY = """
             DELETE FROM account WHERE id = ?
            """;

    private static final String FIND_BY_EMAIL_QUERY = FIND_ALL_QUERY +
            """
                      WHERE email = ?
                    """;

    private final SimpleConnectionPool connectionPool = SimpleConnectionPool.getInstance();
    private final Mapper<ResultSet, Account> toAccountMapper = new RSToAccountMapper();
    private final Validator<Account> updateAccountValidator = new UpdateAccountValidator();

    private SimpleAccountDao() {
    }

    public static SimpleAccountDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Account> findById(Long id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
        ) {
            prepStatement.setObject(1, id);

            ResultSet resultSet = prepStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(toAccountMapper.map(resultSet));
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> findAll() {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)
        ) {
            ResultSet resultSet = statement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (resultSet.next()) {
                accounts.add(toAccountMapper.map(resultSet));
            }
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account save(Account entity) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setObject(1, entity.getEmail());

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
    public void update(Account entity) {
        updateAccountValidator.validate(entity);
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            statement.setObject(1, entity.getEmail());
            statement.setObject(2, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
        ) {
            statement.setObject(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL_QUERY);
        ) {
            statement.setObject(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(toAccountMapper.map(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
