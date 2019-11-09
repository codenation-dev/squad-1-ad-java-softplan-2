CREATE TABLE user_account (
    id bigserial PRIMARY KEY,
    name varchar(50) NOT NULL,
    email varchar(100) UNIQUE NOT NULL,
    password varchar(50) NOT NULL,
    token  varchar(50) UNIQUE NOT NULL
);