package com.rycardofarias.estacionamentoveiculoapi.repositories;

import com.rycardofarias.estacionamentoveiculoapi.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
