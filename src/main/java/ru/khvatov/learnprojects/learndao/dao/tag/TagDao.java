package ru.khvatov.learnprojects.learndao.dao.tag;

import ru.khvatov.learnprojects.learndao.dao.Dao;
import ru.khvatov.learnprojects.learndao.entity.Tag;

import java.util.List;

public interface TagDao<K extends Long, E extends Tag> extends Dao<K, E> {

    List<E> findAllByAccountId(Long accountId);

}
