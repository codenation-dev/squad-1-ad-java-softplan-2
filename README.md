# squad-1-ad-java-softplan-2


### Criar BD no Docker para rodar a aplicação

Usar os seguintes comandos para criar o Banco de Dados antes de iniciar a aplicação: <br>
`docker pull postgres`

Criar um PostgreSQL com **user:postgres** e **password:postgress**: <br>
`docker run --name aceleradev-postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres`

`docker start aceleradev-postgres`

Entar no bash dentro da imagem: <br>
`docker exec -it aceleradev-postgres bash`

Executar psql: <br>
`psql -U postgres`

Criar o Banco de Dados: <br>
`CREATE DATABASE squad1_db;`

Sair: <br>
`\q`
`exit`

Agora é possível conectar ao banco usando: 
>url=jdbc:postgresql://localhost:5432/squad1_db <br>
username=postgres <br>
password=postgres <br>

### Migrações com Flyway

Intruções: https://flywaydb.org/documentation/migrations#naming

Para evoluir o banco de dados, criar scripts SQL dentro da pasta `db/migration` seguindo os critérios da instrução:

- usar nomes no infinitivo;
- separar em migrações diferentes as criações e alterações de tabela, dos inserts de dados.

### Encode de senhas para salvar do BD

Criado uma classe utilitária para gerar senhas encodadas com BCrypt padrão do Spring Security. <br>
A senha do *admin@codenation.com.br* é *admin*