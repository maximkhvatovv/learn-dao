package ru.khvatov.learnprojects.learndao.dao.account;

import ru.khvatov.learnprojects.learndao.dao.Dao;
import ru.khvatov.learnprojects.learndao.entity.Account;

import java.util.Optional;

public interface AccountDao<K extends Long, E extends Account> extends Dao<K, E> {
    Optional<E> findByEmail(String email);
}
