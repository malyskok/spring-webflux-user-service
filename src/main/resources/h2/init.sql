/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

create table users
(
    id      bigint auto_increment,
    name    varchar(50),
    balance int,
    primary key (id)
);

create table user_transaction
(
    id               bigint auto_increment,
    user_id          bigint,
    amount           int,
    transaction_date timestamp,
    foreign key (user_id) references users (id) on delete cascade
);

insert into users
    (name, balance)
values ('sam', 1000),
       ('mike', 1200),
       ('jake', 800),
       ('marshal', 2000);