package com.rycardofarias.estacionamentoveiculoapi.dtos;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioSenhaDto {

    private String senhaAtual;
    private String novaSenha;
    private String confirmarSenha;
}
