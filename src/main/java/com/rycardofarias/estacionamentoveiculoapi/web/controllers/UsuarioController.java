package com.rycardofarias.estacionamentoveiculoapi.web.controllers;

import com.rycardofarias.estacionamentoveiculoapi.servicies.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
}
