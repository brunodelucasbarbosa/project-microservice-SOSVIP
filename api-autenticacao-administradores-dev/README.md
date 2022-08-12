## Repositório do projeto completo: https://gitlab.com/sosvip/sosvip-desafio-modulo2

# 💻 Iniciando a aplicação
1 - Crie na sua máquina um banco de dados PostgreSQL usando o seguinte Script:

```
CREATE DATABASE admin;

CREATE TABLE admin (
	id serial PRIMARY KEY,
	name varchar(150) NOT NULL,
	email varchar(150) NOT NULL UNIQUE,
	password text NOT NULL
);
```

2 - Clone o repositório para sua máquina com o comando abaixo:

```
git clone https://gitlab.com/sosvip/api-autenticacao-administradores.git
```

3 - Importe o projeto usando uma IDE como Eclipse, Intellij, etc...

4 - No caminho "src/main/resources/application.properties" coloque as informações do seu banco de dados local.

5 - Execute o arquivo "AdminsApplication.java" no caminho "src/main/java/com/desafio/m2".

