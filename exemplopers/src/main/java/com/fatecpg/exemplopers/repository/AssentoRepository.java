package com.fatecpg.exemplopers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecpg.exemplopers.model.Assento;

@Repository
public interface AssentoRepository extends JpaRepository<Assento, Long> {

    List<Assento> findBySalaId(Long salaId);

    List<Assento> findBySalaIdAndFila(Long salaId, String fila);
}
