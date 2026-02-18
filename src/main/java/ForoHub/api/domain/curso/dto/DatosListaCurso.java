package ForoHub.api.domain.curso.dto;

import ForoHub.api.domain.curso.Categoria;
import ForoHub.api.domain.curso.model.Curso;

public record DatosListaCurso(String nombre, Categoria categoria) {

    public DatosListaCurso(Curso datos){
        this(datos.getNombre(),datos.getCategoria());
    }
}
