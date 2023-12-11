package ru.khvatov.learnprojects.learndao;

import ru.khvatov.learnprojects.learndao.dao.account.AccountDao;
import ru.khvatov.learnprojects.learndao.dao.account.impl.SimpleAccountDao;
import ru.khvatov.learnprojects.learndao.dao.product.ProductDao;
import ru.khvatov.learnprojects.learndao.dao.product.impl.SimpleProductDao;
import ru.khvatov.learnprojects.learndao.dao.tag.TagDao;
import ru.khvatov.learnprojects.learndao.dao.tag.impl.SimpleTagDao;
import ru.khvatov.learnprojects.learndao.entity.Account;
import ru.khvatov.learnprojects.learndao.entity.Product;
import ru.khvatov.learnprojects.learndao.entity.Tag;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Thread client = new Thread(() -> {
            AccountDao<Long, Account> accountDao = SimpleAccountDao.getInstance();
            Optional<Account> optionalAccount = accountDao.findByEmail("greg@gmail.com");

            TagDao<Long, Tag> tagDao = SimpleTagDao.getInstance();
            optionalAccount.ifPresent(account -> {
                List<Tag> tagsOfAccount = tagDao.findAllByAccountId(account.getId());
                String desc = "%s has the following tags:%n%s".formatted(
                        account,
                        tagsOfAccount.stream()
                                .map(Tag::toString)
                                .collect(joining("\n", "`\n", "\n`")));
                System.out.println(desc);
            });

            ProductDao<Long, Product> productDao = SimpleProductDao.getInstance();
            optionalAccount.ifPresent(
                    account -> {
                        List<Product> productsOfAccount = productDao.findAllByAccountId(account.getId());
                        String desc = "%s has the following products:%n%s".formatted(
                                account,
                                productsOfAccount.stream()
                                        .map(Product::toString)
                                        .collect(joining("\n", "`\n", "\n`")));
                        System.out.println(desc);
                    }
            );
        });

        client.start();
        client.join();

    }

}
