# Estacionamento Veiculo API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) 

## Project Description

The Estacionamento API is a Spring Boot application.

## Technologies Used


- Spring Boot 3.2.0
- PostgreSQL
- Swagger
- JWT for authentication
- Lombok
- JUnit 5
- AssertJ

## Installation

Follow these steps to set up and run the Estacionamento Veículo API in your local environment:

1. Clone the repository: git clone https://github.com/rycardofarias/estacionamento-veiculos-api.git
2. Configure a PostgreSQL database and update the connection information in the `application.properties` file.
3. Install dependencies: mvn install
4. Run the application: mvn spring-boot:run

## Endepoints

### Usuarios

- POST:  Usuarios - create
  
http://localhost:8080/api/v1/usuarios


{
    "username": "ricardo@email.com",
    "password": "123456"
}


- POST: Auth user
  
http://localhost:8080/api/v1/auth

{
    "username": "admin3@email.com",
    "password": "123456"
}

- GET: Usuarios - get id (Authorization
Bearer Token)

http://localhost:8080/api/v1/usuarios/15 

- PATCH: Usuarios - update password (Authorization
Bearer Token)

http://localhost:8080/api/v1/usuarios/15

{
    "senhaAtual": "12345678",
    "novaSenha" : "123456",
    "confirmarSenha": "123456"

}

- GET: Usuario - get all (Authorization
Bearer Token)

http://localhost:8080/api/v1/usuarios

### Clientes (Authorization Bearer Token)

- POST: Clientes - create

http://localhost:8080/api/v1/clientes

{
    "nome": "Manoel Gomes",
    "cpf": "19067900001"
}

- GET: Clientes - get id

http://localhost:8080/api/v1/clientes/17

- GET: Clientes - get all

http://localhost:8080/api/v1/clientes?page=0

- GET: Clientes - detahes

http://localhost:8080/api/v1/clientes/detalhes

### Vagas

- POST: Vagas - create

http://localhost:8080/api/v1/vagas

{
    "codigo": "A-04",
    "status": "LIVRE"
}

- GET: Vaga - get by codigo

http://localhost:8080/api/v1/vagas/A-01

### Estacionamentos

- POST: Estacionamentos - check-in

http://localhost:8080/api/v1/estacionamentos/check-in

{
    "clienteCpf": "85212472067",
    "placa": "WWC-8800",
    "marca": "Hyundai",
    "modelo": "HB20",
    "cor": "Branco"
}

- GET: Estacionamentos - get checkin por recibo

http://localhost:8080/api/v1/estacionamentos/check-in/20231218-004406

- PUT: Estacionamento - check-out

http://localhost:8080/api/v1/estacionamentos/check-out/20231216-011156

- GET: Estacionamentos - relatorio

http://localhost:8080/api/v1/estacionamentos/relatorio
