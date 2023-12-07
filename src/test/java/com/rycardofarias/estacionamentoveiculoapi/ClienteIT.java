package com.rycardofarias.estacionamentoveiculoapi;

import com.rycardofarias.estacionamentoveiculoapi.dtos.ClienteCreateDto;
import com.rycardofarias.estacionamentoveiculoapi.dtos.ClienteResponseDto;
import com.rycardofarias.estacionamentoveiculoapi.web.exceptions.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/clientes/clientes-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "/sql/clientes/clientes-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class ClienteIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void criarCliente_ComDadosValidos_RetornarClientecComStatus201() {
        ClienteResponseDto responseBody = testClient
                .post()
                .uri("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAutentication.getHeaderAuthorization(testClient, "toby@email.com", "123456"))
                .bodyValue(new ClienteCreateDto("Tobias Ferreira", "90285202014"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ClienteResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isNotNull();
        Assertions.assertThat(responseBody.getNome()).isEqualTo("Tobias Ferreira");
        Assertions.assertThat(responseBody.getCpf()).isEqualTo("90285202014");
    }

    @Test
    public void criarCliente_ComDadosValidos_RetornarErroMessageStatus409() {
        ErrorMessage  responseBody = testClient
                .post()
                .uri("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAutentication.getHeaderAuthorization(testClient, "toby@email.com", "123456"))
                .bodyValue(new ClienteCreateDto("Tobias Ferreira", "16615177064"))
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(409);

    }

    @Test
    public void criarCliente_ComDadosInvalidos_RetornarErroMessageStatus422() {
        ErrorMessage  responseBody = testClient
                .post()
                .uri("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAutentication.getHeaderAuthorization(testClient, "toby@email.com", "123456"))
                .bodyValue(new ClienteCreateDto("", ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);


        responseBody = testClient
                .post()
                .uri("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAutentication.getHeaderAuthorization(testClient, "toby@email.com", "123456"))
                .bodyValue(new ClienteCreateDto("Rick", "00000000000"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);


        responseBody = testClient
                .post()
                .uri("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAutentication.getHeaderAuthorization(testClient, "toby@email.com", "123456"))
                .bodyValue(new ClienteCreateDto("Rick", "902.852.020-14"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

    }

    @Test
    public void criarCliente_ComUsuarioNaoPermitido_RetornarErroMessageStatus403() {
        ErrorMessage  responseBody = testClient
                .post()
                .uri("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAutentication.getHeaderAuthorization(testClient, "admin@email.com", "123456"))
                .bodyValue(new ClienteCreateDto("Tobias Ferreira", "90285202014"))
                .exchange()
                .expectStatus().isEqualTo(403)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }

    @Test
    public void buscarCliente_ComIdExistentePeloAdmin_RetornarClienteComStatus200() {
        ClienteResponseDto  responseBody = testClient
                .get()
                .uri("/api/v1/clientes/10")
                .headers(JwtAutentication.getHeaderAuthorization(testClient, "admin@email.com", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ClienteResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isEqualTo(10);
    }

    @Test
    public void buscarCliente_ComIdInexistentePeloAdmin_RetornarMessageComStatus404() {
        ErrorMessage  responseBody = testClient
                .get()
                .uri("/api/v1/clientes/0")
                .headers(JwtAutentication.getHeaderAuthorization(testClient, "admin@email.com", "123456"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }

    @Test
    public void buscarCliente_ComIdInexistentePeloCliente_RetornarMessageComStatus403() {
        ErrorMessage  responseBody = testClient
                .get()
                .uri("/api/v1/clientes/0")
                .headers(JwtAutentication.getHeaderAuthorization(testClient, "ricardo@email.com", "123456"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }
}
