package com.rycardofarias.estacionamentoveiculoapi.web.controllers;

import com.rycardofarias.estacionamentoveiculoapi.dtos.ClienteCreateDto;
import com.rycardofarias.estacionamentoveiculoapi.dtos.ClienteResponseDto;
import com.rycardofarias.estacionamentoveiculoapi.dtos.mappers.ClienteMapper;
import com.rycardofarias.estacionamentoveiculoapi.entities.Cliente;
import com.rycardofarias.estacionamentoveiculoapi.jwt.JwtUserDetails;
import com.rycardofarias.estacionamentoveiculoapi.servicies.ClienteService;
import com.rycardofarias.estacionamentoveiculoapi.servicies.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity<ClienteResponseDto> create(@RequestBody @Valid ClienteCreateDto clienteCreateDto,
                                                    @AuthenticationPrincipal JwtUserDetails userDetails) {

        Cliente cliente = ClienteMapper.toCliente(clienteCreateDto);
        cliente.setUsuario(usuarioService.buscarPorId(userDetails.getId()));
        clienteService.salvar(cliente);
        return ResponseEntity.status(201).body(ClienteMapper.toDto(cliente));
    }
}
