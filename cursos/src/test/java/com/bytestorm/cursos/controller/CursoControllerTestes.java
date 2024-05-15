package com.bytestorm.cursos.controller;


import com.bytestorm.cursos.entity.Curso;
import com.bytestorm.cursos.exception.CursoNaoEncontradoException;
import com.bytestorm.cursos.exception.NomeCursoRepetidoException;
import com.bytestorm.cursos.mapper.CursoMapper;
import com.bytestorm.cursos.record.CursoRecord;
import com.bytestorm.cursos.service.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.bytestorm.cursos.entity.Curso.AreaConhecimento.ENGENHARIA_SOFTWARE;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static com.bytestorm.cursos.common.CursoConstantes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.bytestorm.cursos.record.CursoRecord.*;

@WebMvcTest
public class CursoControllerTestes {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CursoService cursoService;

    @Test
    public void criarCurso_ComDadosValidos_RetornaCurso() throws Exception {
        Curso curso = CursoMapper.toEntity(DTO_CURSO);
        when(cursoService.cadastrarCurso(any(Curso.class))).thenReturn(curso);

        mockMvc.perform(
                post("/api/v1/cursos/cadastrar")
                        .content(objectMapper.writeValueAsString(curso)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is(curso.getNome())))
                .andExpect(jsonPath("$.quantidadeHoras", is(curso.getQuantidadeHoras())))
                .andExpect(jsonPath("$.professor", is(curso.getProfessor())))
                .andExpect(jsonPath("$.areaConhecimento", is(curso.getAreaConhecimento().toString())))
                .andExpect(jsonPath("$.ativo", is(curso.isAtivo())));
    }

    @Test
    public void criarCurso_ComDadosInvalidos_RetornaException() throws Exception {
        Curso curso = CursoMapper.toEntity(DTO_VAZIO);

        mockMvc.perform(
                post("/api/v1/cursos/cadastrar")
                        .content(objectMapper.writeValueAsString(curso))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void criarCurso_ComNomeRepetido_RetornaException() throws Exception {

        Curso curso = CursoMapper.toEntity(DTO_CURSO);
        when(cursoService.cadastrarCurso(any(Curso.class))).thenThrow(new NomeCursoRepetidoException("Nome do curso já existe"));

        mockMvc.perform(
                        post("/api/v1/cursos/cadastrar")
                                .content(objectMapper.writeValueAsString(curso))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void buscarCursoPorId_ComIdValido_RetornaCurso() throws Exception {

        Curso curso = CursoMapper.toEntity(DTO_CURSO);
        when(cursoService.buscarCursoPorId(1L)).thenReturn(curso);

        mockMvc.perform(get("/api/v1/cursos/buscar-curso-id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(curso.getNome())))
                .andExpect(jsonPath("$.quantidadeHoras", is(curso.getQuantidadeHoras())))
                .andExpect(jsonPath("$.professor", is(curso.getProfessor())))
                .andExpect(jsonPath("$.areaConhecimento", is(curso.getAreaConhecimento().toString())))
                .andExpect(jsonPath("$.ativo", is(curso.isAtivo())));
    }

    @Test
    public void buscarCursoPorId_ComIdInvalido_RetornarException() throws Exception {

        when(cursoService.buscarCursoPorId(15L)).thenThrow(new CursoNaoEncontradoException("Curso com id = 8, não foi encontrado"));

        mockMvc.perform(get("/api/v1/cursos/buscar-curso-id/15"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void alterarProfessor_ComIdValido_RetornaCurso() throws Exception {

        Long id = 1L;
        Curso curso = CursoMapper.toEntity(DTO_CURSO);
        curso.setProfessor(DTO_PROFESSOR.professor());

        when(cursoService.alterarProfessor(id, DTO_PROFESSOR.professor())).thenReturn(curso);

        mockMvc.perform(
                        patch("/api/v1/cursos/alterar-professor/" + id)
                                .content(objectMapper.writeValueAsString(DTO_PROFESSOR))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.professor", is(DTO_PROFESSOR.professor())));
    }
}