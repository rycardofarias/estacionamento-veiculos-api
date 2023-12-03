package com.rycardofarias.estacionamentoveiculoapi.dtos.mappers;

import com.rycardofarias.estacionamentoveiculoapi.dtos.ClienteCreateDto;
import com.rycardofarias.estacionamentoveiculoapi.dtos.ClienteResponseDto;
import com.rycardofarias.estacionamentoveiculoapi.entities.Cliente;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {

    public static Cliente toCliente(ClienteCreateDto createDto) {
        return new ModelMapper().map(createDto, Cliente.class);
    }

    public static ClienteResponseDto toDto(Cliente cliente) {
        return new ModelMapper().map(cliente, ClienteResponseDto.class);
    }
}
