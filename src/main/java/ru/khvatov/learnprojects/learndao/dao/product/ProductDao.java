package ru.khvatov.learnprojects.learndao.dao.product;

import ru.khvatov.learnprojects.learndao.dao.Dao;
import ru.khvatov.learnprojects.learndao.entity.Product;

import java.util.List;

public interface ProductDao<K extends Long, E extends Product> extends Dao<K, E> {
    List<E> findAllByTagId(K tagId);

    List<E> findAllByAccountId(K accountId);

    List<E> findAllByAccountAndTag(K accountId, K tagId);

    List<E> findAllByAccountIdAndName(K accountId, String name);

    List<E> findAllByAccountIdWhichIsNotEnoughByCount(K accountId);

}
