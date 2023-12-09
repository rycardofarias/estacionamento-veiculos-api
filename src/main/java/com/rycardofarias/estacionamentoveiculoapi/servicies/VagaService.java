package com.rycardofarias.estacionamentoveiculoapi.servicies;

import com.rycardofarias.estacionamentoveiculoapi.entities.Vaga;
import com.rycardofarias.estacionamentoveiculoapi.exceptions.CodigoUniqueViolationException;
import com.rycardofarias.estacionamentoveiculoapi.exceptions.EntityNotFoundException;
import com.rycardofarias.estacionamentoveiculoapi.repositories.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class VagaService {

    private final VagaRepository vagaRepository;

    @Transactional
    public Vaga salvar(Vaga vaga) {
        try {
            return vagaRepository.save(vaga);
        } catch (DataIntegrityViolationException e) {
            throw new CodigoUniqueViolationException(String.format("Vaga com código {%s} já cadastrado",
                    vaga.getCodigo()));
        }
    }
    @Transactional(readOnly = true)
    public Vaga buscarPorCodigo(String codigo) {
        return vagaRepository.findByCodigo(codigo).orElseThrow(() -> new EntityNotFoundException(
                String.format("Vaga com código {%s} não foi encontrada", codigo)));
    }
}
