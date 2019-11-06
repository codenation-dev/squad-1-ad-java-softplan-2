-- remove dados para poder inserir coluna not null
delete from usuario;

ALTER TABLE usuario
ADD COLUMN token TEXT NOT NULL;