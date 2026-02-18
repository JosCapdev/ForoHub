package ForoHub.api.domain.perfil.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroPerfil(@NotBlank String nombre) {
}
