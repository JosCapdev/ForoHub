package ForoHub.api.domain.respuesta.dto;

import ForoHub.api.domain.respuesta.model.Respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        String nombreAutor,
        String tituloTopico,
        String solucion
) {
    public DatosDetalleRespuesta(Respuesta datos) {
        this(
                datos.getId(),
                datos.getMensaje(),
                datos.getFechaCreacion(),
                datos.getAutor().getNombre(),
                datos.getTopico().getTitulo(),
                datos.isSolucion() ? "Si" : "No"
        );
    }
}
