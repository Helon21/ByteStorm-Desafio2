package com.bytestorm.cursos.controller;

import com.bytestorm.cursos.entity.Curso;

import com.bytestorm.cursos.mapper.CursoMapper;
import com.bytestorm.cursos.service.CursoService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bytestorm.cursos.record.CursoRecord.*;

import java.util.List;
import java.util.stream.Collectors;


@Tag(name = "Cursos", description = "Contém todas as operações relativas a gestão de curso")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cursos/")
public class CursoController {

    private final CursoService cursoService;

    @Operation(summary = "Buscar todos os cursos", description = "Recurso para retornar todos os cursos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoRequisicaoDTO.class))),

            }
    )
    @GetMapping("buscar-cursos")
    public ResponseEntity<List<CursoRequisicaoDTO>> buscarTodosCursos() {
        List<Curso> cursos = cursoService.buscarTodosCursos();
        List<CursoRequisicaoDTO> cursosDTO = cursos.stream().map(CursoMapper::toCursoRequisicaoDTO).collect(Collectors.toList());
        return ResponseEntity.ok(cursosDTO);
    }

    @Operation(summary = "Buscar curso por id", description = "Recurso para retornar um curso referente ao ID específicado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoRequisicaoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Curso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("buscar-curso-id/{id}")
    public ResponseEntity<CursoRequisicaoDTO> buscarCursoPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarCursoPorId(id);
        CursoRequisicaoDTO cursoDTO = CursoMapper.toCursoRequisicaoDTO(curso);
        return ResponseEntity.ok(cursoDTO);
    }

    @Operation(summary = "Cadastro de curso", description = "Recurso para cadastrar curso",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Curso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoRequisicaoDTO.class))),
                    @ApiResponse(responseCode = "409", description = "Nome do curso já existe")
            }
    )
    @PostMapping("cadastrar")
    public ResponseEntity<CursoRequisicaoDTO> cadastrarCurso(@RequestBody @Valid CursoRequisicaoDTO dto) {
        Curso curso = CursoMapper.toEntity(dto);
        cursoService.cadastrarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(CursoMapper.toCursoRequisicaoDTO(curso));
    }

    @Operation(summary = "Alterar Professor", description = "Recurso para fazer a alteração somente o atributo professor",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Professor alterado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoRequisicaoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Curso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PatchMapping("alterar-professor/{id}")
    public ResponseEntity<AlterarProfessorDto> alterarProfessor(@PathVariable Long id, @RequestBody @Valid AlterarProfessorDto dto) {
        String professor = dto.professor();
        Curso novoProfessor = cursoService.alterarProfessor(id, professor);
        AlterarProfessorDto cursoAtualizado = CursoMapper.toAlterarProfessorDto(novoProfessor);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoAtualizado);
    }

    @Operation(summary = "Inabilitar Curso", description = "Recurso para inabilitar um curso, recebendo como parâmetro um id, para localizar o curso a ser modificado",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Curso Inabilitado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoRequisicaoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Curso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "304", description = "Curso já inativo",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PatchMapping("inabilitar-curso/{id}")
    public ResponseEntity<CursoRequisicaoDTO> inabilitarCurso(@PathVariable Long id) {
        Curso curso = cursoService.inabilitarCurso(id);
        CursoRequisicaoDTO cursoDTO = CursoMapper.toCursoRequisicaoDTO(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDTO);
    }
}