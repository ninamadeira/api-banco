# api-banco

Processamento de correntista e conta corrente. E consumidor rabbitmq.

### Banco de Dados
```
O banco de dados é h2 em memória. Muito bom para desenvolvimento e testes.
```
### Validação 
```
Validação de cpf e cnpj já existente em banco de dados.
Validação do número da conta já existe em banco de dados.
```
### Gerador
```
Geradores de número conta e score.
```
### Consultas e listagens
```
Consulta do correntista:
GET
http://localhost:8082/correntistas/1
{
    "id": 1,
    "nome": "Nina Madeira",
    "tipo": "PESSOAFISICA",
    "cpfOuCnpj": "11111111111",
    "score": 3,
    "contaCorrente": {
        "id": 1,
        "numero": 815329,
        "agencia": "222-2",
        "tipo": "C",
        "faixaScore": "o limite de cheque especial será de R$ 1000,00 e o limite do cartão de crédito criado será de R$ 200,00."
    }
}

Lista todos os correntistas
GET
http://localhost:8082/correntistas
[
    {
        "id": 1,
        "nome": "Nina Madeira",
        "tipo": 1,
        "cpfOuCnpj": "11111111111",
        "score": 3
    },
    {
        "id": 2,
        "nome": "React Ltda",
        "tipo": 2,
        "cpfOuCnpj": "62865792000114",
        "score": 6
    },
    {
        "id": 3,
        "nome": "VUE Ltda",
        "tipo": 2,
        "cpfOuCnpj": "20886317000184",
        "score": 3
    },
    {
        "id": 4,
        "nome": "Luis Kamon",
        "tipo": 1,
        "cpfOuCnpj": "11443913006",
        "score": 3
    }
]

Tem opção com paginação:
GET
http://localhost:8082/correntistas/page

Lista todas contas:
GET
http://localhost:8082/contascorrentes
[
    {
        "id": 1,
        "numero": 815329,
        "agencia": "222-2",
        "tipo": "C",
        "faixaScore": "o limite de cheque especial será de R$ 1000,00 e o limite do cartão de crédito criado será de R$ 200,00."
    },
    {
        "id": 2,
        "numero": 431971,
        "agencia": "222-2",
        "tipo": "E",
        "faixaScore": "o limite de cheque especial será de R$ 2000,00 e o limite do cartão de crédito criado será de R$ 2000,00."
    },
    {
        "id": 3,
        "numero": 272801,
        "agencia": "222-2",
        "tipo": "E",
        "faixaScore": "o limite de cheque especial será de R$ 1000,00 e o limite do cartão de crédito criado será de R$ 200,00."
    },
    {
        "id": 4,
        "numero": 558591,
        "agencia": "222-2",
        "tipo": "C",
        "faixaScore": "o limite de cheque especial será de R$ 1000,00 e o limite do cartão de crédito criado será de R$ 200,00."
    }
]

Consulta conta:
GET
http://localhost:8082/contascorrentes/1

    {
        "id": 1,
        "numero": 815329,
        "agencia": "222-2",
        "tipo": "C",
        "faixaScore": "o limite de cheque especial será de R$ 1000,00 e o limite do cartão de crédito criado será de R$ 200,00."
    }


```
### Variável de ambiente para agência
```
è necessário criar a seguinta variável de ambiente para agência:
AGENCIABANCO = "123-6"

application.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

agencia=${AGENCIABANCO}
```
### Implementação
```
Spring Boot Rest API
Consumidor RabbitMQ
Validadores
Geradores
H2 Banco de dados
Design Pattern
Versão Java: 11
```