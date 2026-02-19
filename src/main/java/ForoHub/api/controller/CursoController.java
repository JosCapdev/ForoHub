package ForoHub.api.controller;

import ForoHub.api.domain.curso.dto.DatosActualizarCurso;
import ForoHub.api.domain.curso.dto.DatosDetalleCurso;
import ForoHub.api.domain.curso.dto.DatosListaCurso;
import ForoHub.api.domain.curso.dto.DatosRegistroCurso;
import ForoHub.api.domain.curso.model.Curso;
import ForoHub.api.domain.curso.service.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datos) {
        DatosDetalleCurso curso = cursoService.registrarCurso(datos);
        return ResponseEntity.ok(curso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaCurso>> listarCurso(@PageableDefault(size = 10,sort = {"nombre"}, direction = Sort.Direction.ASC)Pageable paginacion) {
        Page<DatosListaCurso> cursos = cursoService.listarCursos(paginacion);
        return ResponseEntity.ok(cursos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosDetalleCurso> actualizarCurso(@PathVariable Long id,
                                                        @RequestBody @Valid DatosActualizarCurso datos) {
        Curso curso = cursoService.actualizarCurso(id, datos);
        return ResponseEntity.ok(new DatosDetalleCurso(curso));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleCurso> detallarCurso(@PathVariable Long id) {
        DatosDetalleCurso curso = cursoService.detallarCurso(id);
        return ResponseEntity.ok(curso);
    }

}