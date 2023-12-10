package com.rycardofarias.estacionamentoveiculoapi.servicies;

import com.rycardofarias.estacionamentoveiculoapi.entities.ClienteVaga;
import com.rycardofarias.estacionamentoveiculoapi.repositories.ClienteVagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClienteVagaService {

    private final ClienteVagaRepository clienteVagaRepository;

    @Transactional
    public ClienteVaga salvar(ClienteVaga clienteVaga) {
        return clienteVagaRepository.save(clienteVaga);
    }
}
