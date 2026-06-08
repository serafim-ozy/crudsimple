package com.fatecpg.exemplopers.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade Assento - lado DONO do relacionamento OneToMany/ManyToOne com Sala.
 *
 * Restricao de unicidade composta: (sala_id, fila, numero) - dois assentos
 * com a mesma fila e numero nao podem coexistir na mesma sala.
 *
 * fetch = LAZY no ManyToOne: melhor performance; carrega a Sala apenas quando
 * acessada.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(
    name = "assentos",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_assento_sala_fila_numero",
        columnNames = {"sala_id", "fila", "numero"}
    )
)
@Getter
@Setter
@NoArgsConstructor
public class Assento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O número do assento é obrigatório.")
    @Size(max = 5)
    @Column(nullable = false, length = 5)
    private String numero;

    @NotBlank(message = "A fila é obrigatória.")
    @Size(max = 5)
    @Column(nullable = false, length = 5)
    private String fila;

    @NotNull(message = "A sala é obrigatória.")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;
}
