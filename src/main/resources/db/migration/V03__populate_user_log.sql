ALTER TABLE log DROP CONSTRAINT log_api_key_key;

-- password = codenation
INSERT INTO user_account
(name, email, "password", api_key, created_at, modified_at)
VALUES('João', 'joao@codenation.com', 'be45936562a69075903b189cd8c46fcc', '3e7eb343-dd9e-42d0-bce5-064fd84d7938', '2019-12-03 11:10:34.542', '2019-12-03 11:10:34.542');

-- password = codenation
INSERT INTO user_account
(name, email, "password", api_key, created_at, modified_at)
VALUES('Maria', 'maria@codenation.com', 'be45936562a69075903b189cd8c46fcc', 'e9e75456-b636-432c-ab92-27c58be98518', '2019-12-03 11:10:34.542', '2019-12-03 11:10:34.542');

INSERT INTO log
("source", title, description, environment, "level", status, api_key, created_at, modified_at)
VALUES('localhost', 'Log Info', 'description info', 'DEVELOPMENT', 'INFO', 'ACTIVE', '3e7eb343-dd9e-42d0-bce5-064fd84d7938', '2019-12-03 10:00:00.000', '2019-12-03 10:00:00.000');

INSERT INTO log
("source", title, description, environment, "level", status, api_key, created_at, modified_at)
VALUES('localhost', 'Log Info', 'description info', 'DEVELOPMENT', 'INFO', 'ACTIVE', '3e7eb343-dd9e-42d0-bce5-064fd84d7938', '2019-12-03 11:00:00.000', '2019-12-03 11:00:00.000');

INSERT INTO log
("source", title, description, environment, "level", status, api_key, created_at, modified_at)
VALUES('localhost', 'Log Warn', 'description warn', 'HOMOLOGATION', 'WARN', 'ACTIVE', '3e7eb343-dd9e-42d0-bce5-064fd84d7938', '2019-12-03 12:00:00.000', '2019-12-03 12:00:00.000');

INSERT INTO log
("source", title, description, environment, "level", status, api_key, created_at, modified_at)
VALUES('localhost', 'Log Error', 'description error', 'DEVELOPMENT', 'ERROR', 'ACTIVE', '3e7eb343-dd9e-42d0-bce5-064fd84d7938', '2019-12-03 13:00:00.000', '2019-12-03 13:00:00.000');
