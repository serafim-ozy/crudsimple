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
import org.springframework.web.bind.annotation.RestController;

import com.fatecpg.exemplopers.model.AssentoAcessivel;
import com.fatecpg.exemplopers.service.AssentoAcessivelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/assentos/acessiveis")
public class AssentoAcessivelController {

    private final AssentoAcessivelService assentoAcessivelService;

    public AssentoAcessivelController(AssentoAcessivelService assentoAcessivelService) {
        this.assentoAcessivelService = assentoAcessivelService;
    }

    @GetMapping
    public List<AssentoAcessivel> getAll() {
        return assentoAcessivelService.findAll();
    }

    @GetMapping("/{id}")
    public AssentoAcessivel getById(@PathVariable Long id) {
        return assentoAcessivelService.findById(id);
    }

    @PostMapping
    public ResponseEntity<AssentoAcessivel> create(@Valid @RequestBody AssentoAcessivel assentoAcessivel) {
        AssentoAcessivel criado = assentoAcessivelService.save(assentoAcessivel);
        return ResponseEntity.created(URI.create("/assentos/acessiveis/" + criado.getId())).body(criado);
    }

    @PutMapping("/{id}")
    public AssentoAcessivel update(@PathVariable Long id, @Valid @RequestBody AssentoAcessivel assentoAcessivel) {
        return assentoAcessivelService.update(id, assentoAcessivel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assentoAcessivelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
