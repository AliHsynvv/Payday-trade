create table user
(
    id       int auto_increment
        primary key,
    name     varchar(30)  not null,
    email    varchar(255) not null,
    password varchar(255) not null,
    cash     double       null
)
    auto_increment = 5;

INSERT INTO payday_trade.user (id, name, email, password, cash) VALUES (1, 'Aliakbar', 'Huseynov', '$2a$10$lkn7Sq78TlJkpWvs6PCpN.0cztwxd47zZ1QtvBdY23qEvwN/qmJt6', 10908.11);
