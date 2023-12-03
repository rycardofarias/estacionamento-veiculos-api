package com.rycardofarias.estacionamentoveiculoapi.repositories;

import com.rycardofarias.estacionamentoveiculoapi.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
