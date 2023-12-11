package ru.khvatov.learnprojects.learndao.mapper;

public interface Mapper<F, T> {
    T map(F obj);
}
