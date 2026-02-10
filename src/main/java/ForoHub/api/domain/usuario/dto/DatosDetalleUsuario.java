package ForoHub.api.domain.usuario.dto;

import ForoHub.api.domain.usuario.model.Usuario;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String email
) {
    public DatosDetalleUsuario(Usuario datos) {
        this(datos.getId(),datos.getNombre(),datos.getEmail());
    }
}
