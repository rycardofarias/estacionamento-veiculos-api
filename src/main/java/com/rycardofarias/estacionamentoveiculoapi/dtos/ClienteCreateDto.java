package com.rycardofarias.estacionamentoveiculoapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ClienteCreateDto {

    @NotBlank
    @Size(min = 5, max = 200)
    private String nome;
    @NotBlank
    @Size(min = 11, max = 11)
    @CPF
    private String cpf;
}
