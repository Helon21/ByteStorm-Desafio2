package com.bytestorm.cursos.record;

import com.bytestorm.cursos.entity.Curso;
import lombok.Builder;

public class CursoRecord {

    @Builder
    public record CursoRequisicaoDTO(
            Long id,
            String nome,
            Integer quantidadeHoras,
            String professor,
            Curso.AreaConhecimento areaConhecimento,
            boolean ativo
    ) {
    }
}
