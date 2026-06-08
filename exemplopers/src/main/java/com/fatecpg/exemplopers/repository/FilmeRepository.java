package com.fatecpg.exemplopers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecpg.exemplopers.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByTituloContainingIgnoreCase(String trecho);

    List<Filme> findByGeneroIgnoreCase(String genero);
}
