# SoftLog

## Sobre o projeto
Esse projeto é o back-end do projeto final do curso de Java da Codenation em parceria com a Softplan.

O objetivo principal do projeto é implementar um sistema para centralizar registros de erros de aplicações de onde podem ser monitorados diferentes serviços e camadas de aplicações (backend, frontend, mobile, desktop), facilitando a gestão e tomada de decisões.

## Começando
Essas instruções fornecerão uma cópia do projeto em execução na sua máquina para fins de desenvolvimento e teste.

#### Pré-requisitos

Para rodar esse projeto no modo development você precisará ter um ambiente com Java 8+ instalado. Para utilizar a database você precisará ter instalado o Postgres ou Docker para subir uma imagem do Postgres.

- Java 8+
- Maven
- Docker / Docker-Compose ou Postgres

#### Instalando
##### 1. Clonando o repositório com HTTPS
`$ git clone https://github.com/codenation-dev/squad-1-ad-java-softplan-2.git`

##### 2. Criação do banco de dados no Docker para rodar a aplicação, com docker-compose  
Executar comando abaixo na pasta raiz do projeto:  

`docker-compose up aceleradev-postgres`

##### 3. Agora é possível conectar ao banco usando: 
>url=jdbc:postgresql://localhost:5432/squad1_db <br>
username=postgres <br>
password=postgres <br>

##### 4. Rodar a aplicação Spring Boot

Executar comando abaixo na pasta raiz do projeto:

`mvn spring-boot:run`


## Endpoints

Documentação da API: http://localhost:8080/swagger-ui.html

#### User

* Create an User

| End Point | Method | Params | URL Params | Sucess Response |
|-----------|--------|--------|------------|-----------------|
|/api /users|  POST  | userDTO|            |  Code: 201 - Created | 

#### Log

* Create a Log

| End Point | Method | Params | URL Params | Sucess Response | 
|-----------|--------|--------|------------|-----------------|
|/api /logs  |  POST  | logDTO |           | Code: 201 - Created  | 

* Get a list of log aggregates

| End Point | Method | Params | URL Params | Sucess Response |
|-----------|--------|--------|------------|-----------------|
|/api /logs  |  GET  |        |            | Code: 200 - OK  | 

* Get a log aggregate details 

| End Point | Method | Params | URL Params | Sucess Response |
|-----------|--------|--------|------------|-----------------|
|/api /logs /{id} |  GET  |        |       | Code: 200 - OK  | 

* Delete a log aggregate

| End Point | Method | Params | URL Params | Sucess Response | 
|-----------|--------|--------|------------|-----------------|
|/api /logs  | DELETE|   id   |            |  Code: 200 - OK | 

* Archive a log aggregate

| End Point | Method | Params | URL Params | Sucess Response | 
|-----------|--------|--------|------------|-----------------|
|/api /logs  | PATCH|   id   |            |  Code: 200 - OK | 

#### Auth

* Login in application

| End Point | Method | Params | URL Params | Sucess Response |
|------------|---------|----------|---------------|--------------------|
|/api /auth/login|POST |loginRequestDTO|          |  Code: 204 - No Content  | 


  ## Construído com
- [Spring boot]([https://spring.io/guides/gs/spring-boot/](https://spring.io)) - Framework
 - [Docker]([https://www.docker.com/](https://www.docker.com/)) - Inicialização do banco de dados
 - [JUnit]([https://junit.org/junit5/](https://junit.org/junit5/)) - Framework de testes
 - [Heroku]([https://www.heroku.com/](https://www.heroku.com/)) - PaaS usada na produção
 - [Postgres]([https://www.postgresql.org/](https://www.postgresql.org/)) - Banco de dados
 - [Maven]([https://maven.apache.org/](https://maven.apache.org/)) - Gerenciador de dependências
 - [Flyway]([https://flywaydb.org/](https://flywaydb.org/)) - Migração de banco de dados
 - [Lombok]([https://projectlombok.org/](https://projectlombok.org/)) - Biblioteca de annotations
 - [Swagger]([https://swagger.io/](https://swagger.io/)) - Documentação da API
 - [Mapstruct]([https://mapstruct.org/](https://mapstruct.org/)) - Biblioteca de mapeamento
 - [Hibernate]([https://hibernate.org/search/](https://hibernate.org/search/)) - ORM
 - [Postman](https://www.getpostman.com/) - Testador de APIs e endpoints
 

## Autores 

 - Aguilar Figueira Dias
 - André Guilherme Kunitz
 - Douglas Klafke Scheibler
 - Eduardo de Carvalho
 - Karolini Rosine Pereira
 
## Licença
  
Este projeto está licenciado sob a licença MIT.