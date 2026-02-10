package ForoHub.api.domain.curso.dto;

import ForoHub.api.domain.curso.model.Curso;

public record DatosDetalleCurso(
        Long id,
        String nombre,
        String categoria
) {
    public DatosDetalleCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria().name());
    }
}
