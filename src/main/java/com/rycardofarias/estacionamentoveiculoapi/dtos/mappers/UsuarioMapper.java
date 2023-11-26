package com.rycardofarias.estacionamentoveiculoapi.dtos.mappers;

import com.rycardofarias.estacionamentoveiculoapi.dtos.UsuarioCreateDto;
import com.rycardofarias.estacionamentoveiculoapi.dtos.UsuarioResponseDto;
import com.rycardofarias.estacionamentoveiculoapi.entities.Usuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createDto) {
        return new ModelMapper().map(createDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDto.class);
    }
}
