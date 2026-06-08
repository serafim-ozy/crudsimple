package com.fatecpg.exemplopers.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade Filme.
 *
 * Relacionamento: ManyToMany com Ator (lado dono - define a @JoinTable).
 * Atende ao requisito: "ao menos 1 relacionamento ManyToMany".
 */
@Entity
@Table(name = "filmes")
@Getter
@Setter
@NoArgsConstructor
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String titulo;

    /** Duracao do filme em minutos. */
    @NotNull(message = "A duração é obrigatória.")
    @Positive(message = "A duração deve ser positiva.")
    @Column(nullable = false)
    private Integer duracao;

    @NotBlank(message = "O gênero é obrigatório.")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String genero;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "filme_ator",
        joinColumns = @JoinColumn(name = "filme_id"),
        inverseJoinColumns = @JoinColumn(name = "ator_id")
    )
    @JsonIgnoreProperties("filmes")
    private List<Ator> atores = new ArrayList<>();
}
