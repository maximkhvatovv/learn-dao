package ru.khvatov.learnprojects.learndao.util.database;

import ru.khvatov.learnprojects.learndao.util.database.exeption.NoSuchPropertyException;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import static ru.khvatov.learnprojects.learndao.util.database.DataSourceProperties.PropertyKey.CONNECTION_POOL_SIZE;

public class SimpleConnectionPool implements ConnectionPool {
    private static final int DEFAULT_POOL_SIZE = 10;
    private static final SimpleConnectionPool INSTANCE = new SimpleConnectionPool();
    private final BlockingQueue<Connection> pool;
    private final List<Connection> connections;

    public static SimpleConnectionPool getInstance() {
        return INSTANCE;
    }

    private SimpleConnectionPool() {
        int poolSize = getPoolSizeFromPropertiesFile();
        connections = new CopyOnWriteArrayList<>();
        pool = new ArrayBlockingQueue<>(poolSize);
        initPool(poolSize);
    }

    private static int getPoolSizeFromPropertiesFile() {
        String propertyValue;
        try {
            propertyValue = DataSourceProperties.get(CONNECTION_POOL_SIZE);
            return Integer.parseInt(propertyValue);
        } catch (NoSuchPropertyException e) {
            return DEFAULT_POOL_SIZE;
        }
    }

    private void initPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            Connection connection = Connections.open();
            Connection proxy = wrapWithAddToPoolOnClose(connection);
            pool.add(proxy);
            connections.add(connection);
        }
    }

    private Connection wrapWithAddToPoolOnClose(Connection connection) {
        return (Connection) Proxy.newProxyInstance(
                SimpleConnectionPool.class.getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    String sourceMethodName = Connection.class.getDeclaredMethod("close").getName();
                    if (method.getName().equals(sourceMethodName)) {
                        return pool.add(connection);
                    } else {
                        return method.invoke(connection, args);
                    }
                }
        );
    }

    @Override
    public Connection getConnection() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        for (Connection connection : connections) {
            connection.close();
        }
    }
}
