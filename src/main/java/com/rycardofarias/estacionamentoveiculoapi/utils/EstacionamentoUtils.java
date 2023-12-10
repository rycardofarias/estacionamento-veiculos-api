package com.rycardofarias.estacionamentoveiculoapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EstacionamentoUtils {

    public static String gerarRecibo(){
        LocalDateTime dateTime = LocalDateTime.now();
        String recibo = dateTime.toString().substring(0, 19);
        return recibo.replace("-", "")
                .replace(":", "")
                .replace("T", "");
    }
}
