package ForoHub.api.domain.topico.dto;

import ForoHub.api.domain.topico.StatusTopico;
import ForoHub.api.domain.topico.model.Topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String nombreAutor,
        String nombreCurso
) {
    public DatosDetalleTopico(Topico datos) {
        this(datos.getId(),datos.getTitulo(),datos.getMensaje(),datos.getFechaCreacion()
                ,datos.getStatus(),datos.getAutor().getNombre(),datos.getCurso().getNombre()
                );
    }
}
