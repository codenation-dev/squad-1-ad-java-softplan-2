CREATE TABLE log (
	id BIGSERIAL PRIMARY KEY,
	source VARCHAR(50) NOT NULL,
	title VARCHAR(255) NOT NULL,
	description TEXT NOT NULL,
    environment VARCHAR(12) NOT NULL,
    level VARCHAR(10) NOT NULL,
    status VARCHAR(8) NOT NULL,
    user_account_id INT8,
	created_at TIMESTAMP,
    modified_at TIMESTAMP
);
ALTER TABLE log ADD CONSTRAINT fk_log_user FOREIGN KEY (user_account_id) REFERENCES user_account;