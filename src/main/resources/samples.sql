insert into account (email)
values ('ivan@yandex.ru'),
       ('alex@yandex.ru'),
       ('vovik@gmail.com'),
       ('pesok@gmail.com'),
       ('greg@gmail.com');
insert into tag (account_id, name)
values (1, 'крупы'),
       (1, 'гигиена'),
       (1, 'консервы'),
       (1, 'напитки');
insert into tag (account_id, name)
values (2, 'крупы'),
       (2, 'гигиена'),
       (2, 'консервы'),
       (2, 'напитки');
insert into tag (account_id, name)
values (3, 'крупы'),
       (3, 'гигиена'),
       (3, 'консервы'),
       (3, 'напитки');
insert into tag (account_id, name)
values (4, 'крупы'),
       (4, 'гигиена'),
       (4, 'консервы'),
       (4, 'напитки');
insert into tag (account_id, name)
values (5, 'крупы'),
       (5, 'гигиена'),
       (5, 'консервы'),
       (5, 'напитки');


insert into product (account_id, tag_id, name, available_count, comfort_count)
values (1, (select id from tag where tag.account_id = 1 and tag.name = 'крупы'), 'Рис бурый', 0, 2),
       (1, (select id from tag where tag.account_id = 1 and tag.name = 'крупы'), 'Булгур 5пак*8', 0, 4),
       (1, (select id from tag where tag.account_id = 1 and tag.name = 'крупы'), 'Геркулес', 1, 4),
       (1, (select id from tag where tag.account_id = 1 and tag.name = 'крупы'),'Гречка', 1, 3),
       (1, (select id from tag where tag.account_id = 1 and tag.name = 'гигиена'),'Пена для бритья', 1, 3),
       (1, (select id from tag where tag.account_id = 1 and tag.name = 'гигиена'),'Бритвы одноразовые 10 шт', 1, 5),
       (1, (select id from tag where tag.account_id = 1 and tag.name = 'напитки'),'Вода 0.5л', 1, 3),
       (1, (select id from tag where tag.account_id = 1 and tag.name = 'напитки'),'Вода 5л', 1, 2),
       (1, (select id from tag where tag.account_id = 1 and tag.name = 'консервы'),'Горошек консервированный', 1, 4),
       (1, (select id from tag where tag.account_id = 1 and tag.name = 'консервы'),'Кукуруза консервированный', 1, 2),
       (1, (select id from tag where tag.account_id = 1 and tag.name = 'консервы'),'Фасоль консервированная', 1, 4),
       (1, (select id from tag where tag.account_id = 1 and tag.name = 'консервы'),'Сгущенка', 1, 2);

insert into product (account_id, tag_id, name, available_count, comfort_count)
values (2, (select id from tag where tag.account_id = 2 and tag.name = 'крупы'), 'Рис бурый', 0, 2),
       (2, (select id from tag where tag.account_id = 2 and tag.name = 'крупы'), 'Булгур 5пак*8', 0, 4),
       (2, (select id from tag where tag.account_id = 2 and tag.name = 'крупы'), 'Геркулес', 1, 4),
       (2, (select id from tag where tag.account_id = 2 and tag.name = 'крупы'),'Гречка', 1, 3),
       (2, (select id from tag where tag.account_id = 2 and tag.name = 'гигиена'),'Пена для бритья', 1, 3),
       (2, (select id from tag where tag.account_id = 2 and tag.name = 'гигиена'),'Бритвы одноразовые 10 шт', 1, 5),
       (2, (select id from tag where tag.account_id = 2 and tag.name = 'напитки'),'Вода 0.5л', 1, 3),
       (2, (select id from tag where tag.account_id = 2 and tag.name = 'напитки'),'Вода 5л', 1, 2),
       (2, (select id from tag where tag.account_id = 2 and tag.name = 'консервы'),'Горошек консервированный', 1, 4),
       (2, (select id from tag where tag.account_id = 2 and tag.name = 'консервы'),'Кукуруза консервированный', 1, 2),
       (2, (select id from tag where tag.account_id = 2 and tag.name = 'консервы'),'Фасоль консервированная', 1, 4),
       (2, (select id from tag where tag.account_id = 2 and tag.name = 'консервы'),'Сгущенка', 1, 2);

insert into product (account_id, tag_id, name, available_count, comfort_count)
values (3, (select id from tag where tag.account_id = 3 and tag.name = 'крупы'), 'Рис бурый', 0, 2),
       (3, (select id from tag where tag.account_id = 3 and tag.name = 'крупы'), 'Булгур 5пак*8', 0, 4),
       (3, (select id from tag where tag.account_id = 3 and tag.name = 'крупы'), 'Геркулес', 1, 4),
       (3, (select id from tag where tag.account_id = 3 and tag.name = 'крупы'),'Гречка', 1, 3),
       (3, (select id from tag where tag.account_id = 3 and tag.name = 'гигиена'),'Пена для бритья', 1, 3),
       (3, (select id from tag where tag.account_id = 3 and tag.name = 'гигиена'),'Бритвы одноразовые 10 шт', 1, 5),
       (3, (select id from tag where tag.account_id = 3 and tag.name = 'напитки'),'Вода 0.5л', 1, 3),
       (3, (select id from tag where tag.account_id = 3 and tag.name = 'напитки'),'Вода 5л', 1, 2),
       (3, (select id from tag where tag.account_id = 3 and tag.name = 'консервы'),'Горошек консервированный', 1, 4),
       (3, (select id from tag where tag.account_id = 3 and tag.name = 'консервы'),'Кукуруза консервированный', 1, 2),
       (3, (select id from tag where tag.account_id = 3 and tag.name = 'консервы'),'Фасоль консервированная', 1, 4),
       (3, (select id from tag where tag.account_id = 3 and tag.name = 'консервы'),'Сгущенка', 1, 2);

insert into product (account_id, tag_id, name, available_count, comfort_count)
values (4, (select id from tag where tag.account_id = 4 and tag.name = 'крупы'), 'Рис бурый', 0, 2),
       (4, (select id from tag where tag.account_id = 4 and tag.name = 'крупы'), 'Булгур 5пак*8', 0, 4),
       (4, (select id from tag where tag.account_id = 4 and tag.name = 'крупы'), 'Геркулес', 1, 4),
       (4, (select id from tag where tag.account_id = 4 and tag.name = 'крупы'),'Гречка', 1, 3),
       (4, (select id from tag where tag.account_id = 4 and tag.name = 'гигиена'),'Пена для бритья', 1, 3),
       (4, (select id from tag where tag.account_id = 4 and tag.name = 'гигиена'),'Бритвы одноразовые 10 шт', 1, 5),
       (4, (select id from tag where tag.account_id = 4 and tag.name = 'напитки'),'Вода 0.5л', 1, 3),
       (4, (select id from tag where tag.account_id = 4 and tag.name = 'напитки'),'Вода 5л', 1, 2),
       (4, (select id from tag where tag.account_id = 4 and tag.name = 'консервы'),'Горошек консервированный', 1, 4),
       (4, (select id from tag where tag.account_id = 4 and tag.name = 'консервы'),'Кукуруза консервированный', 1, 2),
       (4, (select id from tag where tag.account_id = 4 and tag.name = 'консервы'),'Фасоль консервированная', 1, 4),
       (4, (select id from tag where tag.account_id = 4 and tag.name = 'консервы'),'Сгущенка', 1, 2);

insert into product (account_id, tag_id, name, available_count, comfort_count)
values (5, (select id from tag where tag.account_id = 5 and tag.name = 'крупы'), 'Рис бурый', 0, 2),
       (5, (select id from tag where tag.account_id = 5 and tag.name = 'крупы'), 'Булгур 5пак*8', 0, 4),
       (5, (select id from tag where tag.account_id = 5 and tag.name = 'крупы'), 'Геркулес', 1, 4),
       (5, (select id from tag where tag.account_id = 5 and tag.name = 'крупы'),'Гречка', 1, 3),
       (5, (select id from tag where tag.account_id = 5 and tag.name = 'гигиена'),'Пена для бритья', 1, 3),
       (5, (select id from tag where tag.account_id = 5 and tag.name = 'гигиена'),'Бритвы одноразовые 10 шт', 1, 5),
       (5, (select id from tag where tag.account_id = 5 and tag.name = 'напитки'),'Вода 0.5л', 1, 3),
       (5, (select id from tag where tag.account_id = 5 and tag.name = 'напитки'),'Вода 5л', 1, 2),
       (5, (select id from tag where tag.account_id = 5 and tag.name = 'консервы'),'Горошек консервированный', 1, 4),
       (5, (select id from tag where tag.account_id = 5 and tag.name = 'консервы'),'Кукуруза консервированный', 1, 2),
       (5, (select id from tag where tag.account_id = 5 and tag.name = 'консервы'),'Фасоль консервированная', 1, 4),
       (5, (select id from tag where tag.account_id = 5 and tag.name = 'консервы'),'Сгущенка', 1, 2);
