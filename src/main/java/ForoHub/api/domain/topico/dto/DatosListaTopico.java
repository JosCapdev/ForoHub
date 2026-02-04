package ForoHub.api.domain.topico.dto;

import ForoHub.api.domain.topico.StatusTopico;
import ForoHub.api.domain.topico.model.Topico;

import java.time.LocalDateTime;

public record DatosListaTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico statusTopico,
        String nombreAutor,
        String nombreCurso
) {
    public DatosListaTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getStatus(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}
