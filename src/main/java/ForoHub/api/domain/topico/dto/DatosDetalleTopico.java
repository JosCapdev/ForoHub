package ForoHub.api.domain.topico.dto;

import ForoHub.api.domain.topico.model.Topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Long idUsuario,
        Long idCurso
) {
    public DatosDetalleTopico(Topico datos) {
        this(datos.getId(),datos.getTitulo(),datos.getMensaje(),datos.getFechaCreacion()
                ,datos.getAutor().getId(),datos.getCurso().getId()
                );
    }
}
