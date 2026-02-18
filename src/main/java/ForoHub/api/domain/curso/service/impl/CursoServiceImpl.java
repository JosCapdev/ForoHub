package ForoHub.api.domain.curso.service.impl;

import ForoHub.api.domain.curso.dto.DatosActualizarCurso;
import ForoHub.api.domain.curso.dto.DatosDetalleCurso;
import ForoHub.api.domain.curso.dto.DatosListaCurso;
import ForoHub.api.domain.curso.dto.DatosRegistroCurso;
import ForoHub.api.domain.curso.model.Curso;
import ForoHub.api.domain.curso.repository.CursoRepository;
import ForoHub.api.domain.curso.service.CursoService;
import ForoHub.api.infra.exceptions.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl implements CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    @Override
    public DatosDetalleCurso registrarCurso(DatosRegistroCurso datos) {
        var curso = new Curso(datos);
        cursoRepository.save(curso);
        return new DatosDetalleCurso(curso);
    }

    @Override
    public Page<DatosListaCurso> listarCursos(Pageable paginacion) {
        return cursoRepository.findAll(paginacion)
                .map(DatosListaCurso::new);
    }

    @Transactional
    @Override
    public Curso actualizarCurso(Long id, DatosActualizarCurso datos) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Curso no encontrado"));

        if (datos.nombre() == null || datos.nombre().isBlank()) {
            throw new ValidacionException("El nombre del curso no puede estar vacío");
        }
        curso.setNombre(datos.nombre());

        if (datos.categoria() == null) {
            throw new ValidacionException("La categoria no puede estar vacía");
        }
        curso.setCategoria(datos.categoria());

        return curso;
    }

    @Transactional
    @Override
    public void eliminarCurso(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        } else {
            throw new ValidacionException("Curso no encontrado");
        }
    }

    @Override
    public DatosDetalleCurso detallarCurso(Long id) {
        var curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Curso no encontrado"));
        return new DatosDetalleCurso(curso);
    }
}
