package com.rycardofarias.estacionamentoveiculoapi.web.controllers;

import com.rycardofarias.estacionamentoveiculoapi.dtos.VagaCreateDto;
import com.rycardofarias.estacionamentoveiculoapi.dtos.VagaResponseDto;
import com.rycardofarias.estacionamentoveiculoapi.dtos.mappers.VagaMapper;
import com.rycardofarias.estacionamentoveiculoapi.entities.Vaga;
import com.rycardofarias.estacionamentoveiculoapi.servicies.VagaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/vagas")
public class VagaController {

    private final VagaService vagaService;

    @PostMapping
    @PreAuthorize("hasRole ('ADMIN')")
    public ResponseEntity<Void> create(@RequestBody @Valid VagaCreateDto vagaCreateDto) {
        Vaga vaga = VagaMapper.toVagas(vagaCreateDto);
        vagaService.salvar(vaga);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(vaga.getCodigo())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasRole ('ADMIN')")
    public ResponseEntity<VagaResponseDto> getByCodigo(@PathVariable String codigo) {
        Vaga vaga = vagaService.buscarPorCodigo(codigo);
        return ResponseEntity.ok(VagaMapper.toDto(vaga));
    }
}
