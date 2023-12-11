package ru.khvatov.learnprojects.learndao.validator;

public interface Validator<T> {
    void validate(T obj);
}
