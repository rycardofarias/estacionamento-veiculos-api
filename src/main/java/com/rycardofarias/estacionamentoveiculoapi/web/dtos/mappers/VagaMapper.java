package com.rycardofarias.estacionamentoveiculoapi.web.dtos.mappers;

import com.rycardofarias.estacionamentoveiculoapi.web.dtos.VagaCreateDto;
import com.rycardofarias.estacionamentoveiculoapi.web.dtos.VagaResponseDto;
import com.rycardofarias.estacionamentoveiculoapi.entities.Vaga;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VagaMapper {

    public static Vaga toVagas(VagaCreateDto vagaCreateDto) {
        return new ModelMapper().map(vagaCreateDto, Vaga.class);
    }

    public static VagaResponseDto toDto(Vaga vaga) {
        return new ModelMapper().map(vaga, VagaResponseDto.class);
    }
}
