package com.bytestorm.cursos.common;

import com.bytestorm.cursos.entity.Curso;

import static com.bytestorm.cursos.entity.Curso.AreaConhecimento.ENGENHARIA_SOFTWARE;

public class CursoConstantes {

    public static final Curso CURSO1 = new Curso(1L,"Helon", 18, "Girafales", ENGENHARIA_SOFTWARE, true);

    public static final Curso CURSO2 = new Curso(2L, "Helen", 10, "Seu Madruga", ENGENHARIA_SOFTWARE, true);

    public static final Long ID_VALIDO = 1L;

    public static final Long ID_INVALIDO = 745L;

}
