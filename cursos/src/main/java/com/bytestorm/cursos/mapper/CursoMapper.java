package com.bytestorm.cursos.mapper;

import com.bytestorm.cursos.entity.Curso;
import com.bytestorm.cursos.record.CursoRecord;

public class CursoMapper {

    public static CursoRecord.CursoRequisicaoDTO toCursoRequisicaoDTO(Curso curso) {
        return CursoRecord.CursoRequisicaoDTO.builder()
                .id(curso.getId())
                .nome(curso.getNome())
                .quantidadeHoras(curso.getQuantidadeHoras())
                .professor(curso.getProfessor())
                .areaConhecimento(curso.getAreaConhecimento())
                .ativo(curso.isAtivo())
                .build();
    }

    public static Curso toEntity(CursoRecord.CursoRequisicaoDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.nome());
        curso.setQuantidadeHoras(dto.quantidadeHoras());
        curso.setProfessor(dto.professor());
        curso.setAreaConhecimento(dto.areaConhecimento());
        curso.setAtivo(dto.ativo());
        return curso;
    }
}
