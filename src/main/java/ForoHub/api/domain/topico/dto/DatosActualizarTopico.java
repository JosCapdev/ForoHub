package ForoHub.api.domain.topico.dto;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Long idCurso
) {
}
