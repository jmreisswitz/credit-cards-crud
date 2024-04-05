# credit-cards-crud

## Como executar

```bash
$ mvn clean install
docker-compose up -d
```

As migraçoes devem ser aplicadas manualmente, não foi configurado nada como liquibase para facilitar isso ): elas estão em ./migration/


## Endpoints

Todas requisições precisam de um token de autenticação, que é obtido no endpoint de login. O token é passado no header de Authorization na forma "Bearer token"

### POST /api/auth/register
Realiza a criação de um novo usuário

```json
{
  "username": "string",
  "password": "string"
}
```
retorna 200 ok

### POST /api/auth/login

Efectua o login do usuário

```json
{
  "username": "string",
  "password": "string"
}
```

retorna 200 ok com o body

```json
{
  "token": "string"
}
```

### GET /credit-card
Retorna todos os cartões de crédito do usuário. 

```json
[
  {
        "id": 1,
        "lastFourDigits": "4323"
    },
    {
        "id": 2,
        "lastFourDigits": "1234"
    }
]
```

### POST /credit-card
Salva um novo cartão de crédito

body:
```json
{
  "number": "1234123412341234",  // único campo obrigatório
  "cvv": "123",
  "expireMonth": 10,
  "expireYear": 2040,
  "holderName": "some name"
}
```
retorna status 200 ok ou 400 bad request em caso de dados inválidos.

### POST /credit-card/batch
Envia um arquivo csv com vários cartões de crédito para serem salvos

retorna status 200 ok ou 400 bad request em caso de dados inválidos.

## Arquitetura

Foi usado arquitetura hexagonal, com camadas de aplicação, domínio e infraestrutura.

### Domínio
Contém as entidades e regras de negócio. Não depende de nenhuma outra camada.

### Aplicação
Contém os casos de uso, que são as operações que podem ser realizadas no sistema. Depende da camada de domínio.

### Infraestrutura
Contém as implementações dos repositórios e serviços que são necessários para a aplicação funcionar. Depende da camada de aplicação. Também aqui que fica a entrada e saída de dados do sistema (controllers, cron jobs, etc)
