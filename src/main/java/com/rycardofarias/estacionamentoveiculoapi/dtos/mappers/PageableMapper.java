package com.rycardofarias.estacionamentoveiculoapi.dtos.mappers;

import com.rycardofarias.estacionamentoveiculoapi.dtos.PageableDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class PageableMapper {

    public static PageableDto toDto(Page page){
        return new ModelMapper().map(page, PageableDto.class);
    }
}
