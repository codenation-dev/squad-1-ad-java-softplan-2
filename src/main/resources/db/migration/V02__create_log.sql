CREATE TABLE log (
	id BIGSERIAL PRIMARY KEY,
	user_token VARCHAR(50) NOT NULL,
	source VARCHAR(50) NOT NULL,
	title VARCHAR(255) NOT NULL,
	description VARCHAR(500) NOT NULL,
    environment VARCHAR(12) NOT NULL,
    level VARCHAR(10) NOT NULL,
    status VARCHAR(5) NOT NULL,
	created_at TIMESTAMP,
    modified_at TIMESTAMP
);
