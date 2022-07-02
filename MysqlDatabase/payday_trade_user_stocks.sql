create table user_stocks
(
    id          int auto_increment
        primary key,
    user_id     int          not null,
    stock_name  varchar(200) null,
    stock_price double       null,
    constraint user_stocks_user_id_fk
        foreign key (user_id) references user (id)
)
    auto_increment = 4;

INSERT INTO payday_trade.user_stocks (id, user_id, stock_name, stock_price) VALUES (1, 1, 'GOOG', 3455.1400000000003);
