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

import com.fatecpg.exemplopers.model.Assento;
import com.fatecpg.exemplopers.service.AssentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/assentos")
public class AssentoController {

    private final AssentoService assentoService;

    public AssentoController(AssentoService assentoService) {
        this.assentoService = assentoService;
    }

    @GetMapping
    public List<Assento> getAll() {
        return assentoService.findAll();
    }

    @GetMapping("/{id}")
    public Assento getById(@PathVariable Long id) {
        return assentoService.findById(id);
    }

    /** Ex: GET /assentos/busca?salaId=1 */
    @GetMapping("/busca")
    public List<Assento> findBySala(@RequestParam Long salaId) {
        return assentoService.findBySala(salaId);
    }

    @PostMapping
    public ResponseEntity<Assento> create(@Valid @RequestBody Assento assento) {
        Assento criado = assentoService.save(assento);
        return ResponseEntity.created(URI.create("/assentos/" + criado.getId())).body(criado);
    }

    @PutMapping("/{id}")
    public Assento update(@PathVariable Long id, @Valid @RequestBody Assento assento) {
        return assentoService.update(id, assento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
