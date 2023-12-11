package ru.khvatov.learnprojects.learndao.util.database.exeption;

import java.util.NoSuchElementException;

public class NoSuchPropertyException extends NoSuchElementException {
    public NoSuchPropertyException(String s) {
        super(s);
    }

    public NoSuchPropertyException(Throwable cause) {
        super(cause);
    }

    public NoSuchPropertyException(String s, Throwable cause) {
        super(s, cause);
    }
}
