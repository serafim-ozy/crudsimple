package com.fatecpg.exemplopers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "assentos_acessiveis")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class AssentoAcessivel extends Assento {

    @NotNull(message = "A largura especial é obrigatória.")
    @Column(name = "largura_especial", nullable = false)
    private Boolean larguraEspecial;

    @NotNull(message = "A rampa é obrigatória.")
    @Column(name = "possui_rampa", nullable = false)
    private Boolean possuiRampa;
}
