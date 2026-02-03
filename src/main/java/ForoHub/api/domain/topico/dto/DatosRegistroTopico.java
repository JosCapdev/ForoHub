package ForoHub.api.domain.topico.dto;


import java.time.LocalDateTime;

public record DatosRegistroTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Long idUsuario,
        Long idCurso
        ) {

}
