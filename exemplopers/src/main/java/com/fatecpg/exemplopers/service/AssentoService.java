package com.fatecpg.exemplopers.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatecpg.exemplopers.exception.ResourceNotFoundException;
import com.fatecpg.exemplopers.model.Assento;
import com.fatecpg.exemplopers.repository.AssentoRepository;

@Service
public class AssentoService {

    private final AssentoRepository assentoRepository;
    private final SalaService salaService;

    public AssentoService(AssentoRepository assentoRepository, SalaService salaService) {
        this.assentoRepository = assentoRepository;
        this.salaService = salaService;
    }

    @Transactional(readOnly = true)
    public List<Assento> findAll() {
        return assentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Assento findById(Long id) {
        return assentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assento", id));
    }

    @Transactional(readOnly = true)
    public List<Assento> findBySala(Long salaId) {
        // valida que a sala existe antes de listar
        salaService.findById(salaId);
        return assentoRepository.findBySalaId(salaId);
    }

    @Transactional
    public Assento save(Assento assento) {
        // valida a sala referenciada (sem confiar apenas no body do JSON)
        if (assento.getSala() != null && assento.getSala().getId() != null) {
            assento.setSala(salaService.findById(assento.getSala().getId()));
        }
        return assentoRepository.save(assento);
    }

    @Transactional
    public Assento update(Long id, Assento dados) {
        Assento existente = findById(id);
        existente.setNumero(dados.getNumero());
        existente.setFila(dados.getFila());
        if (dados.getSala() != null && dados.getSala().getId() != null) {
            existente.setSala(salaService.findById(dados.getSala().getId()));
        }
        return assentoRepository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!assentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Assento", id);
        }
        assentoRepository.deleteById(id);
    }
}
