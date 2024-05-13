package com.bytestorm.cursos.record;

import com.bytestorm.cursos.entity.Curso;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class CursoRecord {

    @Builder
    public record CursoRequisicaoDTO(
            Long id,
            @NotBlank(message = "nome não pode ser vazio ou nulo")
            String nome,
            @NotNull(message = "quantidade de horas não pode ser vazia ou nula")
            @Min(value=1, message = "Quantia de horas deve ser maior que zero")
            Integer quantidadeHoras,
            @NotBlank(message = "professor não pode estar vazio ou nulo")
            String professor,
            @NotNull(message = "Área de conhecimento não pode ser nula")
            Curso.AreaConhecimento areaConhecimento,
            @NotNull(message = "ativo não pode ser nulo")
            boolean ativo
    ) {
    }
}
