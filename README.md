# squad-1-ad-java-softplan-2

# Endpoints
http://localhost:8080/swagger-ui.html

# gitflow
https://www.campingcoder.com/2018/04/how-to-use-git-flow/

feature->develop->master

feature/def-modelo

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
    util
    exceptions
    bean
        Log @Entity
            -level(info, warn, error)
                -mensagem
                -data
                -dispositivo
    
            