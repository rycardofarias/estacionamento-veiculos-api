package com.rycardofarias.estacionamentoveiculoapi.repositories;

import com.rycardofarias.estacionamentoveiculoapi.entities.ClienteVaga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteVagaRepository extends JpaRepository<ClienteVaga, Long> {
}
