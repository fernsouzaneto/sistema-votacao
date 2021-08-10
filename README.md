
# Sistema Votação
Serviço Rest de votação em pautas usando :

 - Java 11
 - SpringBoot
 - JPA
 - H2 DataBase
 - JDBC

 - Criar Pauta: 
	 - 
	 - EndPoint: http://localhost:8080/pauta/criar
	 - Verbo HTTP: **POST**
	 - Exemplo do Json body:
		 -  `{"descricao":  "pauta 03",
"minutosDuracao":  1,
"flagAtiva":  "S"}`
 - Votar em Pauta: 
	 - 
	 - EndPoint: http://localhost:8080/pauta/votar
	 - Verbo HTTP: **POST**
	 - Exemplo da Requisição: `http://localhost:8080/pauta/votar?cdPauta=4&nuCPFCNPJ=123456875&voto=SIM`
 - Criar Pessoa: 
	 - 
	 - EndPoint: http://localhost:8080/pessoa/cadastrar
	 - Verbo HTTP: **POST**
	 - Exemplo da Json Body:
		 - `{
"nmPessoa":  "test",
"nuCpfCnpj":  "123456789"
}`
 - Listar Resultado da Votação:
	 - 
	 - EndPoint: http://localhost:8080/pauta/resultado
	 - Verbo HTTP: **GET**
	 - Exemplo da Requisição: `http://localhost:8080/pauta/resultado/?cdPauta=2`

 - Listar Pautas:
	 - 
	 - EndPoint: http://localhost:8080/pauta/listar
	 - Verbo HTTP: **GET**
	 - Exemplo da Requisição: `http://localhost:8080/pauta/listar`

 - Listar Pessoas:
	 - 
	 - EndPoint: http://localhost:8080/pessoa/listar
	 - Verbo HTTP: **GET**
	 - Exemplo da Requisição: `http://localhost:8080/pessoa/listar`





