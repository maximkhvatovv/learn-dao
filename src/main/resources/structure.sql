create database purchase_manager;
create table account
(
    id    serial       not null,
    email varchar(120) not null,
    primary key (id),
    unique (email)
);

create table tag
(
    id         serial       not null,
    account_id int          not null,
    name       varchar(100) not null,
    primary key (id),
    foreign key (account_id) references account (id),
    unique (account_id, name)
);
create table product
(
    id              serial       not null,
    account_id      int          not null,
    tag_id          int,
    name            varchar(150) not null,
    available_count int          not null,
    comfort_count   int          not null,
    primary key (id),
    unique (account_id, name),
    foreign key (account_id) references account (id),
    foreign key (tag_id) references tag (id)
);


