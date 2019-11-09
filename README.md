# squad-1-ad-java-softplan-2

## Criar BD no Docker para rodar a aplicação, duas formas de gerenciar o banco postgres com docker

### 1. Gerenciar container com docker-compose (recomendado)  
`docker-compose up aceleradev-postgres`
   
### 2. Gerenciar manualmente o container (não recomendado)  
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

## Agora é possível conectar ao banco usando: 
>url=jdbc:postgresql://localhost:5432/squad1_db <br>
username=postgres <br>
password=postgres <br>

## Migrações com Flyway

Intruções: https://flywaydb.org/documentation/migrations#naming

Para evoluir o banco de dados, criar scripts SQL dentro da pasta `db/migration` seguindo os critérios da instrução:

- usar nomes no infinitivo;
- separar em migrações diferentes as criações e alterações de tabela, dos inserts de dados.

## Encode de senhas para salvar do BD

Criado uma classe utilitária para gerar senhas encodadas com BCrypt padrão do Spring Security. <br>
A senha do *admin@codenation.com.br* é *admin*

# Para importar projeto no eclipse
Import > Maven > Existing Maven Projects

# Endpoints
http://localhost:8080/swagger-ui.html

# gitflow
https://www.campingcoder.com/2018/04/how-to-use-git-flow/

feature->develop->master

###
-definir arquitetura
-definir modelo DDD

dúvidas
-existe separação de logs por empresa/cadastro?

# DDD
## O que (funcionalidades)
    -cadastro usuario
        email, password, sistema gera um token
    -login 
    -esqueci senha
    -resetar senha
    -buscar logs
        ambiente, ordem, data, buscar por (tipo selecionado/texto)
    -apagar log
    -arquivar log
    -detalhar log
        titulo, detalhes, eventos, usuário(token)
    
## Como (descobrir endpoints)
    -cadastro usuario
        -salvar email, senha, gerar token e salvar no banco de dados(tabela usuário)
    -login
        -validar email e senha no banco, caso exista gerar token JWT
    -esqueci senha
        -validar se email existe, caso sim enviar email para usuário com de resetar senha
    -resetar senha
        -receber nova senha e atualizar tabela usuário
    -buscar logs GET:v1/logs/, paginado
        Exemplos 
        -recebe filtros 
            ambiente GET:vi/logs/ambiente/[valor]
            ordem GET:vi/logs/ordem/[valor]
    
Modelo


##############################


resources -> service -> model

Application
    service (gerenciar fluxo)
        chamar model

Domain
    model (logica) 
        Log
            -salvarLog
            -removerLog

Infraestrutura
    rs (resources)
    util
    exceptions
    bean
        Log @Entity
            -level(info, warn, error)
                -mensagem
                -data
                -dispositivo
    
           