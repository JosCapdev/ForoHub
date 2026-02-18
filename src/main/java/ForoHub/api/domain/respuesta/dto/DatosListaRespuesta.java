package ForoHub.api.domain.respuesta.dto;

import ForoHub.api.domain.respuesta.model.Respuesta;

import java.time.LocalDateTime;

public record DatosListaRespuesta(
        String mensaje,
        LocalDateTime fechaCreacion,
        String nombreAutor,
        String tituloTopico,
        String solucion
) {
    public DatosListaRespuesta(Respuesta datos) {
        this(
                datos.getMensaje(),
                datos.getFechaCreacion(),
                datos.getAutor().getNombre(),
                datos.getTopico().getTitulo(),
                datos.isSolucion() ? "Si" : "No"
        );
    }
}
