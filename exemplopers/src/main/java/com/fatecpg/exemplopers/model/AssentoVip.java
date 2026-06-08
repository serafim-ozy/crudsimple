package com.fatecpg.exemplopers.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "assentos_vip")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class AssentoVip extends Assento {

    @NotNull(message = "O preço adicional é obrigatório.")
    @Positive(message = "O preço adicional deve ser positivo.")
    @Column(name = "preco_adicional", nullable = false)
    private BigDecimal precoAdicional;

    @Size(max = 100)
    @Column(name = "servico_incluso", length = 100)
    private String servicoIncluso;
}
