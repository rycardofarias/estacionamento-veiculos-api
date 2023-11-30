package com.rycardofarias.estacionamentoveiculoapi.web.controllers;

import com.rycardofarias.estacionamentoveiculoapi.dtos.mappers.UsuarioLoginDto;
import com.rycardofarias.estacionamentoveiculoapi.jwt.JwtToken;
import com.rycardofarias.estacionamentoveiculoapi.jwt.JwtUserDetailsService;
import com.rycardofarias.estacionamentoveiculoapi.web.exceptions.ErrorMessage;
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

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ap1/v1")
public class AutenticacaoController {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;

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
