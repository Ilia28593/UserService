create table persons
(
    id         bigint NOT NULL generated by default as identity,
    birthday   date NOT NULL,
    password varchar(255) NOT NULL,
    email      varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    role       varchar(255) NOT NULL,
    primary key (id)
);