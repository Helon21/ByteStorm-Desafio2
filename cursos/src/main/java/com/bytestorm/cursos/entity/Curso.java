package com.bytestorm.cursos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nome", unique = true, nullable = false, length = 100)
    private String nome;

    @Column(name="qtde_horas", nullable = false)
    private Integer quantidadeHoras;

    @Column(name="professor", nullable = false)
    private String professor;

    @Enumerated(EnumType.STRING)
    @Column(name="area_conhecimento", length = 50)
    private AreaConhecimento areaConhecimento;

    @Column(name="status")
    private boolean ativo;

    public enum AreaConhecimento {
        ENGENHARIA_SOFTWARE, FÍSICA, MEDICINA
    }
}