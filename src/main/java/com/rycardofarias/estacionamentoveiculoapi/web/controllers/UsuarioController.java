package com.rycardofarias.estacionamentoveiculoapi.web.controllers;

import com.rycardofarias.estacionamentoveiculoapi.dtos.UsuarioCreateDto;
import com.rycardofarias.estacionamentoveiculoapi.dtos.UsuarioResponseDto;
import com.rycardofarias.estacionamentoveiculoapi.dtos.UsuarioSenhaDto;
import com.rycardofarias.estacionamentoveiculoapi.dtos.mappers.UsuarioMapper;
import com.rycardofarias.estacionamentoveiculoapi.entities.Usuario;
import com.rycardofarias.estacionamentoveiculoapi.servicies.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    private ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto) {
        Usuario user = usuarioService.salver(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid
                                                  @RequestBody UsuarioSenhaDto usuarioSenhaDto) {
        Usuario user = usuarioService.editarSenha(id,usuarioSenhaDto.getSenhaAtual(),
                usuarioSenhaDto.getNovaSenha(), usuarioSenhaDto.getConfirmarSenha());

        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }

}
