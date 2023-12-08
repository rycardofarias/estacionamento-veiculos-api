package com.rycardofarias.estacionamentoveiculoapi.repositories;

import com.rycardofarias.estacionamentoveiculoapi.entities.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
}
