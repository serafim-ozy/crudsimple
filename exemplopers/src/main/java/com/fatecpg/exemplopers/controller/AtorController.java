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

import com.fatecpg.exemplopers.model.Ator;
import com.fatecpg.exemplopers.service.AtorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/atores")
public class AtorController {

    private final AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @GetMapping
    public List<Ator> getAll() {
        return atorService.findAll();
    }

    @GetMapping("/{id}")
    public Ator getById(@PathVariable Long id) {
        return atorService.findById(id);
    }

    /** Ex: GET /atores/busca?nome=tom */
    @GetMapping("/busca")
    public List<Ator> findByNome(@RequestParam String nome) {
        return atorService.findByNome(nome);
    }

    @PostMapping
    public ResponseEntity<Ator> create(@Valid @RequestBody Ator ator) {
        Ator criado = atorService.save(ator);
        return ResponseEntity.created(URI.create("/atores/" + criado.getId())).body(criado);
    }

    @PutMapping("/{id}")
    public Ator update(@PathVariable Long id, @Valid @RequestBody Ator ator) {
        return atorService.update(id, ator);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
