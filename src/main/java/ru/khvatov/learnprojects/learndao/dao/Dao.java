package ru.khvatov.learnprojects.learndao.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Dao<K extends Serializable, E> {
    Optional<E> findById(K id);

    List<E> findAll();

    E save(E entity);

    void update(E entity);

    void delete(K id);
}
