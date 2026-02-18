package ForoHub.api.domain.usuario.dto;

import java.util.Set;

public record DatosActualizarUsuario(
        String nombre,
        String pass,
        Set<String> perfiles
) {
}
