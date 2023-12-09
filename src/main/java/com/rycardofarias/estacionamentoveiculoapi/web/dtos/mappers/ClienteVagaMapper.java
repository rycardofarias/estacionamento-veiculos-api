package com.rycardofarias.estacionamentoveiculoapi.web.dtos.mappers;

import com.rycardofarias.estacionamentoveiculoapi.entities.ClienteVaga;
import com.rycardofarias.estacionamentoveiculoapi.web.dtos.EstacionamentoCreateDto;
import com.rycardofarias.estacionamentoveiculoapi.web.dtos.EstacionamentoResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteVagaMapper {

    public static ClienteVaga toClienteVaga(EstacionamentoCreateDto estacionamentoCreateDto) {
        return new ModelMapper().map(estacionamentoCreateDto, ClienteVaga.class);
    }

    public static EstacionamentoResponseDto toDto(ClienteVaga clienteVaga) {
        return new ModelMapper().map(clienteVaga, EstacionamentoResponseDto.class);
    }
}
