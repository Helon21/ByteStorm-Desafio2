package com.bytestorm.cursos.repository;

import com.bytestorm.cursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {


}
