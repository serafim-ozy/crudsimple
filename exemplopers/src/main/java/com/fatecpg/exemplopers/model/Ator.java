package com.fatecpg.exemplopers.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade Ator - HERDA de Pessoa.
 *
 * Como a heranca é JOINED, esta classe gera a tabela "atores" com PK que
 * tambem é FK para "pessoas".
 *
 * Lado inverso (mappedBy) do relacionamento ManyToMany com Filme.
 */
@Entity
@Table(name = "atores")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties("filmes")
public class Ator extends Pessoa {

    @ManyToMany(mappedBy = "atores", fetch = FetchType.EAGER)
    private List<Filme> filmes = new ArrayList<>();
}
