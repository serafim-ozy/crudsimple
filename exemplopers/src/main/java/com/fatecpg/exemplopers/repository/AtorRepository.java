package com.fatecpg.exemplopers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecpg.exemplopers.model.Ator;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {

    List<Ator> findByNomeContainingIgnoreCase(String trecho);
}
