package com.fatecpg.exemplopers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecpg.exemplopers.model.AssentoAcessivel;

@Repository
public interface AssentoAcessivelRepository extends JpaRepository<AssentoAcessivel, Long> {
}
