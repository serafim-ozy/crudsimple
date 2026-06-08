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

import com.fatecpg.exemplopers.model.Sala;
import com.fatecpg.exemplopers.service.SalaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping
    public List<Sala> getAll() {
        return salaService.findAll();
    }

    @GetMapping("/{id}")
    public Sala getById(@PathVariable Long id) {
        return salaService.findById(id);
    }

    /** Ex: GET /salas/busca?capacidadeMinima=100 */
    @GetMapping("/busca")
    public List<Sala> findByCapacidadeMinima(@RequestParam Integer capacidadeMinima) {
        return salaService.findByCapacidadeMinima(capacidadeMinima);
    }

    @PostMapping
    public ResponseEntity<Sala> create(@Valid @RequestBody Sala sala) {
        Sala criada = salaService.save(sala);
        return ResponseEntity.created(URI.create("/salas/" + criada.getId())).body(criada);
    }

    @PutMapping("/{id}")
    public Sala update(@PathVariable Long id, @Valid @RequestBody Sala sala) {
        return salaService.update(id, sala);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        salaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
