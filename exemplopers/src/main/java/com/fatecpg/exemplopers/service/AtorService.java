package com.fatecpg.exemplopers.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatecpg.exemplopers.exception.ResourceNotFoundException;
import com.fatecpg.exemplopers.model.Ator;
import com.fatecpg.exemplopers.repository.AtorRepository;

@Service
public class AtorService {

    private final AtorRepository atorRepository;

    public AtorService(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }

    @Transactional(readOnly = true)
    public List<Ator> findAll() {
        return atorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Ator findById(Long id) {
        return atorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ator", id));
    }

    @Transactional(readOnly = true)
    public List<Ator> findByNome(String trecho) {
        return atorRepository.findByNomeContainingIgnoreCase(trecho);
    }

    @Transactional
    public Ator save(Ator ator) {
        return atorRepository.save(ator);
    }

    @Transactional
    public Ator update(Long id, Ator dados) {
        Ator existente = findById(id);
        existente.setNome(dados.getNome());
        existente.setDataNascimento(dados.getDataNascimento());
        return atorRepository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!atorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ator", id);
        }
        atorRepository.deleteById(id);
    }
}
