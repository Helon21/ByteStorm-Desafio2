package com.bytestorm.cursos.service;

import com.bytestorm.cursos.entity.Curso;
import com.bytestorm.cursos.exception.CursoInativoException;
import com.bytestorm.cursos.exception.CursoNaoEncontradoException;
import com.bytestorm.cursos.exception.NomeCursoRepetidoException;
import com.bytestorm.cursos.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    @Transactional(readOnly = true)
    public List<Curso> buscarTodosCursos() {
        return cursoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Curso buscarCursoPorId(Long id) {
        return cursoRepository.findById(id).orElseThrow(
                () -> new CursoNaoEncontradoException(String.format("Curso com id = %d, não foi encontrado", id))
        );
    }

    @Transactional
    public Curso cadastrarCurso(Curso curso) {
        try {
            return cursoRepository.save(curso);
        } catch (DataIntegrityViolationException e) {
            throw new NomeCursoRepetidoException(String.format("Nome {%s} já existe", curso.getNome()));
        }
    }

    @Transactional
    public Curso alterarProfessor(Long id, String professor) {
        Curso curso = buscarCursoPorId(id);
        curso.setProfessor(professor);
        cursoRepository.save(curso);
        return curso;
    }

    public void inabilitarCurso(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(
                () -> new CursoNaoEncontradoException(String.format("Curso com id = %d, não foi encontrado", id))
        );
        if (curso.isAtivo()) {
            curso.setAtivo(false);
            cursoRepository.save(curso);
        } else {
            throw new CursoInativoException("curso inativo");
        }
    }
}
