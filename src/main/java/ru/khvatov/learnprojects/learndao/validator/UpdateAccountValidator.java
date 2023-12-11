package ru.khvatov.learnprojects.learndao.validator;

import ru.khvatov.learnprojects.learndao.entity.Account;

import static java.util.Objects.isNull;

public class UpdateAccountValidator implements Validator<Account> {
    @Override
    public void validate(Account account) {
        if (isNull(account.getId())) {
            throw new IllegalArgumentException("Id of entity that you want to update must be not null!");
        }
    }
}
