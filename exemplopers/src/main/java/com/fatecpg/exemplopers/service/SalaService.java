package com.fatecpg.exemplopers.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatecpg.exemplopers.exception.ResourceNotFoundException;
import com.fatecpg.exemplopers.model.Sala;
import com.fatecpg.exemplopers.repository.SalaRepository;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Transactional(readOnly = true)
    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Sala findById(Long id) {
        return salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala", id));
    }

    @Transactional(readOnly = true)
    public List<Sala> findByCapacidadeMinima(Integer capacidade) {
        return salaRepository.findByCapacidadeGreaterThanEqual(capacidade);
    }

    @Transactional
    public Sala save(Sala sala) {
        // Garante a coerencia bidirecional (cada assento aponta para esta sala).
        if (sala.getAssentos() != null) {
            sala.getAssentos().forEach(a -> a.setSala(sala));
        }
        return salaRepository.save(sala);
    }

    @Transactional
    public Sala update(Long id, Sala dados) {
        Sala existente = findById(id);
        existente.setNome(dados.getNome());
        existente.setCapacidade(dados.getCapacidade());
        return salaRepository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sala", id);
        }
        salaRepository.deleteById(id);
    }
}
