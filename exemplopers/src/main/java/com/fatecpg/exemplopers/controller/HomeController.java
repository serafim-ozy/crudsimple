package com.fatecpg.exemplopers.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public Map<String, Object> home() {
        return Map.of(
                "application", "exemplopers",
                "version", "0.0.1-SNAPSHOT",
                "message", "API REST de Cinema - use /atores, /filmes, /salas, /assentos",
                "endpoints", Map.of(
                        "atores", "/atores",
                        "filmes", "/filmes",
                        "salas", "/salas",
                        "assentos", "/assentos"
                )
        );
    }
}
