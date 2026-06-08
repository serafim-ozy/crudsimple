package com.fatecpg.exemplopers.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatecpg.exemplopers.model.Filme;
import com.fatecpg.exemplopers.service.FilmeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public List<Filme> getAll() {
        return filmeService.findAll();
    }

    @GetMapping("/{id}")
    public Filme getById(@PathVariable Long id) {
        return filmeService.findById(id);
    }

    /**
     * Ex: GET /filmes/busca?titulo=matrix
     *     GET /filmes/busca?genero=acao
     * Aceita os dois filtros (excludentes).
     */
    @GetMapping("/busca")
    public List<Filme> busca(@RequestParam(required = false) String titulo,
                             @RequestParam(required = false) String genero) {
        if (titulo != null) {
            return filmeService.findByTitulo(titulo);
        }
        if (genero != null) {
            return filmeService.findByGenero(genero);
        }
        return filmeService.findAll();
    }

    @PostMapping
    public ResponseEntity<Filme> create(@Valid @RequestBody Filme filme) {
        Filme criado = filmeService.save(filme);
        return ResponseEntity.created(URI.create("/filmes/" + criado.getId())).body(criado);
    }

    @PutMapping("/{id}")
    public Filme update(@PathVariable Long id, @Valid @RequestBody Filme filme) {
        return filmeService.update(id, filme);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        filmeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
