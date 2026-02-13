package ForoHub.api.domain.usuario.dto;

import ForoHub.api.domain.perfil.model.Perfil;
import ForoHub.api.domain.usuario.model.Usuario;

import java.util.Set;
import java.util.stream.Collectors;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String email,
        Set<String> perfiles
) {
    public DatosDetalleUsuario(Usuario datos) {
        this(
                datos.getId(),
                datos.getNombre(),
                datos.getEmail(),
                datos.getPerfiles().stream()
                        .map(Perfil::getNombre)
                        .collect(Collectors.toSet())
        );
    }
}