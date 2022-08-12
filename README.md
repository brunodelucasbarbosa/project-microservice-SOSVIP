# SOS VIP - Desafio Módulo 2

## 👨🏻‍💻 Sobre o projeto
Desenvolvimento de uma aplicação em Java, com três APIs diferentes. Uma para fazer autenticação de administradores, com validação de token JWT. A outra para cadastro, listagem, atualização e exclusão de clientes. E por último, uma para criação e listagem de pedidos .
Além disso, uso de mensageria (SQS e SES) para envio de e-mail de confirmação de pedido.

O projeto conta também com aplicação Web front-end (React) e Mobile (Kotlin) para utilização da API.
E usa o New Relic para monitoramento e observability, dos servidores e aplicações.

O deploy do sistema foi feito em um cluster kubernetes.

### 💻 Desenvolvedores

Equipe 1: SOS VIP
- [Alessandra Luisi](https://gitlab.com/alluisi)
- [Bianca Andrade](https://gitlab.com/biancaandradee)
- [Bruno de Lucas](https://gitlab.com/brunodelucasbarbosa)
- [Ítalo Costa](https://gitlab.com/s1talo)
- [Rogério Fidencio](https://gitlab.com/rogerio_fidencio)
- [Vitor Eleotério](https://gitlab.com/vitor9263)


## Back-end
Nosso back-end consiste em três APIs com três bancos de dados para o armazenamento de Administradores, Clientes e Pedidos.

### 🚀 Tecnologias
- [JAVA](https://www.java.com/pt-BR/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [JUnit](https://junit.org/junit5/)
- [PostgreSQL](https://www.postgresql.org/)

### ⚙️ Funcionalidades
- Autenticação de Administrador
- Consulta de Clientes
- Cadastro de Clientes
- Atualização de Clientes
- Exclusão de Clientes 
- Consulta de Pedidos
- Criação de Pedidos

#### Repositório do Back-end (Administradores): https://gitlab.com/sosvip/api-autenticacao-administradores
#### Repositório do Back-end (Clientes): https://gitlab.com/sosvip/api-gerenciamento-clientes
#### Repositório do Back-end (Pedidos): https://gitlab.com/sosvip/api-gerenciamento-pedidos


## Front-end - Web
Nosso front-end é uma aplicação WEB que permite o cadastro de administrador e autenticação do mesmo. Além disso, pode-se cadastrar, editar e excluir clientes, incluindo pedidos vinculados e ter acesso a lista dos clientes e pedidos registrados.

### 🚀 Tecnologias
- [Node.js](https://nodejs.org/en/)
- [React.js](https://reactjs.org/)


### ⚙️ Funcionalidades
- Autenticação de Administrador
- Consulta de Clientes
- Cadastro de Clientes
- Atualização de Clientes
- Exclusão de Clientes 
- Consulta de Pedidos
- Criação de Pedidos
