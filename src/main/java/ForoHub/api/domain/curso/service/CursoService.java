package ForoHub.api.domain.curso.service;

import ForoHub.api.domain.curso.dto.DatosActualizarCurso;
import ForoHub.api.domain.curso.dto.DatosDetalleCurso;
import ForoHub.api.domain.curso.dto.DatosListaCurso;
import ForoHub.api.domain.curso.dto.DatosRegistroCurso;
import ForoHub.api.domain.curso.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CursoService {
    DatosDetalleCurso registrarCurso(DatosRegistroCurso datos);

    Page<DatosListaCurso> listarCursos(Pageable paginacion);

    Curso actualizarCurso(Long id, DatosActualizarCurso datos);

    void eliminarCurso(Long id);

    DatosDetalleCurso detallarCurso(Long id);

}
