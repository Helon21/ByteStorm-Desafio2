package com.bytestorm.cursos.service;

import com.bytestorm.cursos.entity.Curso;
import com.bytestorm.cursos.exception.CursoInativoException;
import com.bytestorm.cursos.exception.CursoNaoEncontradoException;
import com.bytestorm.cursos.exception.NomeCursoRepetidoException;
import com.bytestorm.cursos.repository.CursoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static com.bytestorm.cursos.common.CursoConstantes.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTests {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    @Test
    public void buscarTodosCursos_RetornaTodosOsCursos() {

        List<Curso> cursosEsperados = Arrays.asList(CURSO1, CURSO2);

        when(cursoRepository.findAll()).thenReturn(cursosEsperados);

        List<Curso> sut = cursoService.buscarTodosCursos();
        
        assertThat(sut).isEqualTo(cursosEsperados);
    }

    @Test
    public void buscarCursoPorId_ComIdExistente_RetornaCurso() {

        when(cursoRepository.findById(ID_VALIDO)).thenReturn(Optional.of(CURSO1));

        Curso sut = cursoService.buscarCursoPorId(ID_VALIDO);

        assertThat(sut).isEqualTo(CURSO1);
    }

    @Test
    public void buscarCursoPorId_ComIdInexistente_RetornaException() {

        when(cursoRepository.findById(ID_INVALIDO)).thenThrow(new CursoNaoEncontradoException("Curso com id = 8 não foi encontrado"));

        assertThatThrownBy(() -> cursoService.buscarCursoPorId(ID_INVALIDO)).isInstanceOf(CursoNaoEncontradoException.class)
                .hasMessage("Curso com id = 8 não foi encontrado");
    }

    @Test
    public void cadastrarCurso_ComDadosValidos_RetornaCurso() {

        when(cursoRepository.save(CURSO1)).thenReturn(CURSO1);

        Curso sut = cursoService.cadastrarCurso(CURSO1);

        assertThat(sut).isEqualTo(CURSO1);
    }

    @Test
    public void cadastrarCurso_ComNomeRepetido_RetornaException() {

        when(cursoRepository.save(CURSO2)).thenThrow(new DataIntegrityViolationException("Nome já existe"));

        assertThatThrownBy(() -> cursoService.cadastrarCurso(CURSO2))
                .isInstanceOf(NomeCursoRepetidoException.class)
                .hasMessage(String.format("Nome {%s} já existe", CURSO2 .getNome()));
    }

    @Test
    public void alterarProfessor_ComIdValido_RetornaCurso() {
        final String novoProfessor = "Professor Teste";

        when(cursoRepository.findById(ID_VALIDO)).thenReturn(Optional.of(CURSO1));

        Curso sut = cursoService.alterarProfessor(ID_VALIDO, novoProfessor);

        assertThat(sut).isEqualTo(CURSO1);
    }

    @Test
    public void alterarProfessor_ComIdInvalido_RetornaException() {
        final String novoProfessor = "Professor Teste";

        when(cursoRepository.findById(ID_INVALIDO)).thenThrow(new CursoNaoEncontradoException(String.format("Curso com id = %d, não foi encontrado", ID_INVALIDO)));

        assertThatThrownBy(() -> cursoService.alterarProfessor(ID_INVALIDO, novoProfessor))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(String.format("Curso com id = %d, não foi encontrado", ID_INVALIDO));
    }

    @Test
    public void inabilitarCurso_ComIdValido_RetornaCursoInabilitado() {

        when(cursoRepository.findById(ID_VALIDO)).thenReturn(Optional.of(CURSO1));

        Curso sut = cursoService.inabilitarCurso(ID_VALIDO);

        assertThat(sut.isAtivo()).isFalse();
    }

    @Test
    public void inabilitarCurso_ComIdInvalido_RetornaException() {

        when(cursoRepository.findById(ID_INVALIDO)).thenThrow(new CursoNaoEncontradoException(String.format("Curso com id = %d, não foi encontrado", ID_INVALIDO)));


        assertThatThrownBy(() -> cursoService.inabilitarCurso(ID_INVALIDO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Curso com id = %d, não foi encontrado", ID_INVALIDO);
    }

    @Test
    public void inabilitarCurso_CursoComIdValidoECursoJaInabilitado_RetornaException() {

        when(cursoRepository.findById(ID_VALIDO)).thenReturn(Optional.of(CURSO1));

        CURSO1.setAtivo(false);

        assertThatThrownBy(() -> cursoService.inabilitarCurso(ID_VALIDO))
                .isInstanceOf(CursoInativoException.class)
                .hasMessage("curso inativo");
    }
}