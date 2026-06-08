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

import com.fatecpg.exemplopers.model.AssentoVip;
import com.fatecpg.exemplopers.service.AssentoVipService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/assentos/vip")
public class AssentoVipController {

    private final AssentoVipService assentoVipService;

    public AssentoVipController(AssentoVipService assentoVipService) {
        this.assentoVipService = assentoVipService;
    }

    @GetMapping
    public List<AssentoVip> getAll() {
        return assentoVipService.findAll();
    }

    @GetMapping("/{id}")
    public AssentoVip getById(@PathVariable Long id) {
        return assentoVipService.findById(id);
    }

    @PostMapping
    public ResponseEntity<AssentoVip> create(@Valid @RequestBody AssentoVip assentoVip) {
        AssentoVip criado = assentoVipService.save(assentoVip);
        return ResponseEntity.created(URI.create("/assentos/vip/" + criado.getId())).body(criado);
    }

    @PutMapping("/{id}")
    public AssentoVip update(@PathVariable Long id, @Valid @RequestBody AssentoVip assentoVip) {
        return assentoVipService.update(id, assentoVip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assentoVipService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
