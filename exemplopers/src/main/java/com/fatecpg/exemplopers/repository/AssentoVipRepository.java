package com.fatecpg.exemplopers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecpg.exemplopers.model.AssentoVip;

@Repository
public interface AssentoVipRepository extends JpaRepository<AssentoVip, Long> {
}
