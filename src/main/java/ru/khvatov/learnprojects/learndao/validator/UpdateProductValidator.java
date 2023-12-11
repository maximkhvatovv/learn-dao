package ru.khvatov.learnprojects.learndao.validator;

import ru.khvatov.learnprojects.learndao.entity.Product;

import static java.util.Objects.isNull;

public class UpdateProductValidator implements Validator<Product> {
    @Override
    public void validate(Product product) {
        if (isNull(product.getId())) {
            throw new IllegalArgumentException("Id of entity that you want to update must be not null!");
        }
    }
}
