# squad-1-ad-java-softplan-2

## 1. Requisitos (instalar)
- java 8
- docker / docker-compose

## 2. Criar BD no Docker para rodar a aplicação, com docker-compose  
Executar comando abaixo na pasta do projeto:  
`docker-compose up aceleradev-postgres`

Se for preciso recriar o banco devido a reescrita do flyway:

`docker-compose rm --stop aceleradev-postgres`

`docker-compose up aceleradev-postgres`
   
## 3. Agora é possível conectar ao banco usando: 
>url=jdbc:postgresql://localhost:5432/squad1_db <br>
username=postgres <br>
password=postgres <br>

## Migrações com Flyway

Intruções: https://flywaydb.org/documentation/migrations#naming

Para evoluir o banco de dados, criar scripts SQL dentro da pasta `db/migration` seguindo os critérios da instrução:

- usar nomes no infinitivo;
- separar em migrações diferentes as criações e alterações de tabela, dos inserts de dados.

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
    
           