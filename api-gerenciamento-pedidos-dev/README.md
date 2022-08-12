## Repositório do projeto completo: https://gitlab.com/sosvip/sosvip-desafio-modulo2

# 💻 Iniciando a aplicação
1 - Crie na sua máquina um banco de dados PostgreSQL usando o seguinte Script:

```
CREATE DATABASE order;

CREATE TABLE order (
	id serial PRIMARY KEY,
	id_client long NOT NULL,
	total_amount decimal NOT NULL,	
	description text,
	date timestamp NOT NULL,
	status boolean NOT NULL	
);
```

2 - Clone o repositório para sua máquina com o comando abaixo:

```
git clone https://gitlab.com/sosvip/api-gerenciamento-pedidos.git
```

3 - Importe o projeto usando uma IDE como Eclipse, Intellij, etc...

4 - No caminho "src/main/resources/application.properties" coloque as informações do seu banco de dados local.

5 - Execute o arquivo "ApiGerenciamentoPedidosApplication.java" no caminho "src/main/java/br/com/sosvip".
