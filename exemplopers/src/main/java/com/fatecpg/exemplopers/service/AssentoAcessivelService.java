package com.fatecpg.exemplopers.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatecpg.exemplopers.exception.ResourceNotFoundException;
import com.fatecpg.exemplopers.model.AssentoAcessivel;
import com.fatecpg.exemplopers.repository.AssentoAcessivelRepository;

/**
 * Correcao: mesmo problema do AssentoVipService - o save() nao revalidava a Sala.
 * Adicionado o resolve via SalaService para manter coerencia entre as tres services
 * que persistem subtipos de Assento.
 */
@Service
public class AssentoAcessivelService {

    private final AssentoAcessivelRepository assentoAcessivelRepository;
    private final SalaService salaService;

    public AssentoAcessivelService(AssentoAcessivelRepository assentoAcessivelRepository,
                                   SalaService salaService) {
        this.assentoAcessivelRepository = assentoAcessivelRepository;
        this.salaService = salaService;
    }

    @Transactional(readOnly = true)
    public List<AssentoAcessivel> findAll() {
        return assentoAcessivelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public AssentoAcessivel findById(Long id) {
        return assentoAcessivelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssentoAcessivel", id));
    }

    @Transactional
    public AssentoAcessivel save(AssentoAcessivel assentoAcessivel) {
        // valida a sala referenciada
        if (assentoAcessivel.getSala() != null && assentoAcessivel.getSala().getId() != null) {
            assentoAcessivel.setSala(salaService.findById(assentoAcessivel.getSala().getId()));
        }
        return assentoAcessivelRepository.save(assentoAcessivel);
    }

    @Transactional
    public AssentoAcessivel update(Long id, AssentoAcessivel dados) {
        AssentoAcessivel existente = findById(id);

        existente.setNumero(dados.getNumero());
        existente.setFila(dados.getFila());

        if (dados.getSala() != null && dados.getSala().getId() != null) {
            existente.setSala(salaService.findById(dados.getSala().getId()));
        }

        existente.setLarguraEspecial(dados.getLarguraEspecial());
        existente.setPossuiRampa(dados.getPossuiRampa());

        return assentoAcessivelRepository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!assentoAcessivelRepository.existsById(id)) {
            throw new ResourceNotFoundException("AssentoAcessivel", id);
        }
        assentoAcessivelRepository.deleteById(id);
    }
}
