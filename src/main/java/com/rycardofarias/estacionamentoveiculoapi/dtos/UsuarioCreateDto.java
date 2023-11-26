package com.rycardofarias.estacionamentoveiculoapi.dtos;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioCreateDto {

    private String username;
    private String password;
}
