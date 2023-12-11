package ru.khvatov.learnprojects.learndao.validator;

import ru.khvatov.learnprojects.learndao.entity.Tag;

import static java.util.Objects.isNull;

public class UpdateTagValidator implements Validator<Tag> {
    @Override
    public void validate(Tag tag) {
        if (isNull(tag.getId())) {
            throw new IllegalArgumentException("Id of entity that you want to update must be not null!");
        }
    }
}
