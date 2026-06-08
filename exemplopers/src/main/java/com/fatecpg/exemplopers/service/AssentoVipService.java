package com.fatecpg.exemplopers.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatecpg.exemplopers.exception.ResourceNotFoundException;
import com.fatecpg.exemplopers.model.AssentoVip;
import com.fatecpg.exemplopers.repository.AssentoVipRepository;

/**
 * Correcao: o metodo save() nao revalidava a Sala referenciada no JSON, ao contrario
 * do AssentoService. Agora segue o mesmo padrao - resolve a Sala pelo id, evitando
 * que o cliente envie {"sala": {"id": 999}} apontando para uma sala inexistente e
 * derrube a aplicacao com DataIntegrityViolationException.
 */
@Service
public class AssentoVipService {

    private final AssentoVipRepository assentoVipRepository;
    private final SalaService salaService;

    public AssentoVipService(AssentoVipRepository assentoVipRepository, SalaService salaService) {
        this.assentoVipRepository = assentoVipRepository;
        this.salaService = salaService;
    }

    @Transactional(readOnly = true)
    public List<AssentoVip> findAll() {
        return assentoVipRepository.findAll();
    }

    @Transactional(readOnly = true)
    public AssentoVip findById(Long id) {
        return assentoVipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssentoVip", id));
    }

    @Transactional
    public AssentoVip save(AssentoVip assentoVip) {
        // valida a sala referenciada (mesmo tratamento do AssentoService)
        if (assentoVip.getSala() != null && assentoVip.getSala().getId() != null) {
            assentoVip.setSala(salaService.findById(assentoVip.getSala().getId()));
        }
        return assentoVipRepository.save(assentoVip);
    }

    @Transactional
    public AssentoVip update(Long id, AssentoVip dados) {
        AssentoVip existente = findById(id);

        existente.setNumero(dados.getNumero());
        existente.setFila(dados.getFila());

        if (dados.getSala() != null && dados.getSala().getId() != null) {
            existente.setSala(salaService.findById(dados.getSala().getId()));
        }

        existente.setPrecoAdicional(dados.getPrecoAdicional());
        existente.setServicoIncluso(dados.getServicoIncluso());

        return assentoVipRepository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!assentoVipRepository.existsById(id)) {
            throw new ResourceNotFoundException("AssentoVip", id);
        }
        assentoVipRepository.deleteById(id);
    }
}
