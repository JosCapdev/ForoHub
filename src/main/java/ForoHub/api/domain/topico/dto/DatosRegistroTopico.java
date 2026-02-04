package ForoHub.api.domain.topico.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long idUsuario,
        @NotNull Long idCurso
        ) {

}
