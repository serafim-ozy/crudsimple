package com.fatecpg.exemplopers.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade base da hierarquia de pessoas.
 *
 * Mudanca: classe tornada "abstract". Como Pessoa nao possui controller/service proprio e
 * existe apenas para ser estendida por Ator (atendendo ao requisito de herança), torna-la
 * abstract impede instanciacao acidental e comunica melhor a intencao do design - boa
 * pratica de OOP. A entidade continua valida para o JPA: @Entity em classe abstrata é permitido.
 */
@Entity
@Table(name = "pessoas")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @PastOrPresent(message = "A data de nascimento deve ser no passado ou presente.")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
}
