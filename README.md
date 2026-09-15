# Projeto Prática Interdisciplinar

Sistema em desenvolvimento para auxiliar usuários no controle de informações financeiras, com foco inicial em autenticação, cadastro de usuários e registro de movimentações.

## Primeira Fase

Nesta primeira fase do projeto, iremos atuar nas seguintes frentes:

| ID   | História de Usuário                                                                                      | Prioridade |    Pontos |
| ---- | -------------------------------------------------------------------------------------------------------- | ---------- | --------: |
| US01 | Como usuário, quero realizar login no sistema para que minhas informações financeiras fiquem protegidas. | Alta       |         3 |
| US02 | Como usuário, quero realizar meu cadastro para que eu possa utilizar o sistema.                          | Alta       |         3 |
| US03 | Como usuário, quero cadastrar receitas para que eu possa controlar minhas entradas financeiras.          | Alta       |         5 |
| US04 | Como usuário, quero cadastrar despesas para que eu possa controlar meus gastos.                          | Alta       | A definir |

## Objetivo

Construir a base funcional do sistema, garantindo que o usuário consiga acessar a aplicação com segurança e registrar suas principais movimentações financeiras.

## Tecnologias

* Java
* Spring Boot
* Maven
* Spring Security
* Spring Data JPA
* H2 Database
* PostgreSQL

## Como Executar

### Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

* Java
* Maven

### Linux / macOS

Na raiz do projeto, execute:

```bash
./mvnw spring-boot:run
```

### Windows

No Windows, execute:

```bash
mvnw.cmd spring-boot:run
```

##  Estrutura do Projeto

O projeto será desenvolvido de forma incremental, iniciando pelas funcionalidades de autenticação e cadastro de usuários e, posteriormente, pelas funcionalidades de gerenciamento das movimentações financeiras.

##  Status do Projeto
 Em desenvolvimento — Primeira fase.
