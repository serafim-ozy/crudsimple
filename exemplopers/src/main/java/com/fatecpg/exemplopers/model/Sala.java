package com.fatecpg.exemplopers.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade Sala.
 *
 * Relacionamento: OneToMany com Assento (lado inverso, mappedBy).
 * Atende ao requisito: "ao menos 1 relacionamento OneToMany".
 *
 * CascadeType.ALL + orphanRemoval = true: ao remover a sala, seus assentos
 * tambem sao removidos; ao retirar um assento da lista, ele é excluído do banco.
 */
@Entity
@Table(name = "salas")
@Getter
@Setter
@NoArgsConstructor
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da sala é obrigatório.")
    @Size(max = 50)
    @Column(nullable = false, length = 50, unique = true)
    private String nome;

    @NotNull(message = "A capacidade é obrigatória.")
    @Positive(message = "A capacidade deve ser positiva.")
    @Column(nullable = false)
    private Integer capacidade;

    @JsonManagedReference
    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Assento> assentos = new ArrayList<>();
}
