package com.fatecpg.exemplopers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatecpg.exemplopers.exception.ResourceNotFoundException;
import com.fatecpg.exemplopers.model.Ator;
import com.fatecpg.exemplopers.model.Filme;
import com.fatecpg.exemplopers.repository.FilmeRepository;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final AtorService atorService;

    public FilmeService(FilmeRepository filmeRepository, AtorService atorService) {
        this.filmeRepository = filmeRepository;
        this.atorService = atorService;
    }

    @Transactional(readOnly = true)
    public List<Filme> findAll() {
        return filmeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Filme findById(Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme", id));
    }

    @Transactional(readOnly = true)
    public List<Filme> findByTitulo(String trecho) {
        return filmeRepository.findByTituloContainingIgnoreCase(trecho);
    }

    @Transactional(readOnly = true)
    public List<Filme> findByGenero(String genero) {
        return filmeRepository.findByGeneroIgnoreCase(genero);
    }

    @Transactional
    public Filme save(Filme filme) {
        carregarAtoresGerenciados(filme);
        return filmeRepository.save(filme);
    }

    @Transactional
    public Filme update(Long id, Filme dados) {
        Filme existente = findById(id);
        existente.setTitulo(dados.getTitulo());
        existente.setDuracao(dados.getDuracao());
        existente.setGenero(dados.getGenero());

        carregarAtoresGerenciados(dados);
        existente.setAtores(dados.getAtores());

        return filmeRepository.save(existente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!filmeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Filme", id);
        }
        filmeRepository.deleteById(id);
    }

    /**
     * Substitui cada ator do filme (que pode chegar como {"id": 1}) pela
     * instancia gerenciada vinda do banco. Sem isso, o JPA tentaria inserir
     * um novo ator com aquele id e falharia.
     */
    private void carregarAtoresGerenciados(Filme filme) {
        if (filme.getAtores() == null || filme.getAtores().isEmpty()) {
            filme.setAtores(new ArrayList<>());
            return;
        }
        List<Ator> resolvidos = new ArrayList<>();
        for (Ator a : filme.getAtores()) {
            if (a.getId() == null) {
                throw new IllegalArgumentException("Cada ator referenciado deve possuir um id.");
            }
            resolvidos.add(atorService.findById(a.getId()));
        }
        filme.setAtores(resolvidos);
    }
}
