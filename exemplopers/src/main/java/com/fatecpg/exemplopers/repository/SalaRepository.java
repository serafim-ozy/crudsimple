package com.fatecpg.exemplopers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecpg.exemplopers.model.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    Optional<Sala> findByNome(String nome);

    List<Sala> findByCapacidadeGreaterThanEqual(Integer capacidade);
}
