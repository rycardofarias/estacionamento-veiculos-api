package com.rycardofarias.estacionamentoveiculoapi.web.controllers;

import com.rycardofarias.estacionamentoveiculoapi.web.dtos.UsuarioResponseDto;
import com.rycardofarias.estacionamentoveiculoapi.web.dtos.UsuarioLoginDto;
import com.rycardofarias.estacionamentoveiculoapi.jwt.JwtToken;
import com.rycardofarias.estacionamentoveiculoapi.jwt.JwtUserDetailsService;
import com.rycardofarias.estacionamentoveiculoapi.web.exceptions.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Autenticação", description = "Recurso para proceder com a autenticação na API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AutenticacaoController {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "Autenticar na API", description = "Recurso de autenticação na API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso e retorno de um bearer token",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Credenciais inválidas",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Campo(s) Inválido(s)",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping("/auth")
    public ResponseEntity<?> autenticar(
            @RequestBody @Valid UsuarioLoginDto usuarioLoginDto,
            HttpServletRequest request) {

        log.info("Processo de autenticação pelo login {}", usuarioLoginDto.getUsername());

        try{

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            usuarioLoginDto.getUsername(), usuarioLoginDto.getPassword());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = detailsService.getTokenAuthenticated(usuarioLoginDto.getUsername());

            return ResponseEntity.ok(token);

        }catch (AuthenticationException exception) {
            log.warn("Bad Credentials from username '{}'", usuarioLoginDto.getUsername());
        }
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais Inválidas"));
    }
}
