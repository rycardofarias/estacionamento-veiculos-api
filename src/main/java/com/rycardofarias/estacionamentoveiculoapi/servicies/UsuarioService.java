package com.rycardofarias.estacionamentoveiculoapi.servicies;

import com.rycardofarias.estacionamentoveiculoapi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
}
