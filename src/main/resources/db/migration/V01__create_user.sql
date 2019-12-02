CREATE TABLE user_account (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    api_key VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP,
    modified_at TIMESTAMP
);