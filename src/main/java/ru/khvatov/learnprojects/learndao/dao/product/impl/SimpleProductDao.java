package ru.khvatov.learnprojects.learndao.dao.product.impl;

import ru.khvatov.learnprojects.learndao.dao.product.ProductDao;
import ru.khvatov.learnprojects.learndao.validator.UpdateProductValidator;
import ru.khvatov.learnprojects.learndao.validator.Validator;
import ru.khvatov.learnprojects.learndao.entity.Product;
import ru.khvatov.learnprojects.learndao.mapper.Mapper;
import ru.khvatov.learnprojects.learndao.mapper.daolayer.RSToProduct;
import ru.khvatov.learnprojects.learndao.util.database.SimpleConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleProductDao implements ProductDao<Long, Product> {
    private static final SimpleProductDao INSTANCE = new SimpleProductDao();
    private static final String FIND_ALL_QUERY = """
            SELECT id,
                   account_id,
                   tag_id,
                   name,
                   available_count,
                   comfort_count
            FROM product
            """;
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY + """
             FROM product
            WHERE id = ?
            """;
    private static final String INSERT_QUERY = """
            INSERT INTO product(account_id, tag_id, name, available_count, comfort_count)
            VALUES (?, ?, ?, ?, ?)
            """;
    private static final String UPDATE_QUERY = """
            UPDATE product
            SET account_id = ?, tag_id = ?, name = ?, available_count = ?, comfort_count = ?
            WHERE id = ?
            """;
    private static final String DELETE_QUERY = """
            DELETE FROM product
            WHERE id = ?
            """;
    private static final String FIND_ALL_BY_TAG_ID_QUERY = FIND_ALL_QUERY + """
             WHERE tag_id = ?
            """;
    private static final String FIND_ALL_BY_ACCOUNT_ID_QUERY = FIND_ALL_QUERY + """
             WHERE account_id = ?
            """;

    private static final String FIND_ALL_BY_ACCOUNT_ID_AND_TAG_ID_QUERY = FIND_ALL_QUERY + """
             WHERE account_id = ? AND tag_id = ?
            """;

    private static final String FIND_ALL_BY_ACCOUNT_ID_AND_NAME = FIND_ALL_QUERY + """
             WHERE account_id = ? AND name = ?
            """;
    private static final String FIND_ALL_BY_ACCOUNT_ID_WHICH_IS_NOT_ENOUGH_QUERY = FIND_ALL_QUERY + """
             WHERE account_id = ? AND available_count < comfort_count
            """;
    private final SimpleConnectionPool connectionPool = SimpleConnectionPool.getInstance();
    private final Mapper<ResultSet, Product> toProductMapper = new RSToProduct();
    private final Validator<Product> updateProductValidator = new UpdateProductValidator();


    private SimpleProductDao() {
    }

    public static SimpleProductDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Product> findById(Long id) {
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            statement.setObject(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(toProductMapper.map(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        throw new UnsupportedOperationException(
                """
                        The method is not implemented because it can be too expensive to obtain all products from database.
                        """
        );
    }

    @Override
    public Product save(Product entity) {
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setObject(1, entity.getAccountId());
            statement.setObject(2, entity.getTagId());
            statement.setObject(3, entity.getName());
            statement.setObject(4, entity.getAvailableCount());
            statement.setObject(5, entity.getComfortCount());

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
    public void update(Product entity) {
        updateProductValidator.validate(entity);
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(UPDATE_QUERY)
        ) {
            statement.setObject(1, entity.getId());
            statement.setObject(2, entity.getAccountId());
            statement.setObject(3, entity.getTagId());
            statement.setObject(4, entity.getName());
            statement.setObject(5, entity.getAvailableCount());
            statement.setObject(6, entity.getComfortCount());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(DELETE_QUERY)
        ) {
            statement.setObject(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Product> findAllByTagId(Long tagId) {
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(FIND_ALL_BY_TAG_ID_QUERY)
        ) {
            statement.setObject(1, tagId);

            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(toProductMapper.map(resultSet));
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAllByAccountId(Long accountId) {
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(FIND_ALL_BY_ACCOUNT_ID_QUERY)
        ) {
            statement.setObject(1, accountId);

            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(toProductMapper.map(resultSet));
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAllByAccountAndTag(Long accountId, Long tagId) {
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(FIND_ALL_BY_ACCOUNT_ID_AND_TAG_ID_QUERY)
        ) {
            statement.setObject(1, accountId);
            statement.setObject(2, tagId);

            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(toProductMapper.map(resultSet));
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAllByAccountIdAndName(Long accountId, String name) {
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(FIND_ALL_BY_ACCOUNT_ID_AND_NAME)
        ) {
            statement.setObject(1, accountId);
            statement.setObject(2, name);

            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(toProductMapper.map(resultSet));
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAllByAccountIdWhichIsNotEnoughByCount(Long accountId) {
        try (
                Connection c = connectionPool.getConnection();
                PreparedStatement statement = c.prepareStatement(FIND_ALL_BY_ACCOUNT_ID_WHICH_IS_NOT_ENOUGH_QUERY)
        ) {
            statement.setObject(1, accountId);

            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(toProductMapper.map(resultSet));
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
