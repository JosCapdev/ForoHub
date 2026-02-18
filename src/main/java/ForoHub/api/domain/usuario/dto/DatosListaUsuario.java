package ForoHub.api.domain.usuario.dto;

import ForoHub.api.domain.perfil.model.Perfil;
import ForoHub.api.domain.usuario.model.Usuario;

import java.util.Set;
import java.util.stream.Collectors;

public record DatosListaUsuario(
        String nombre,
        String email,
        boolean activo,
        Set <String> perfiles
) {
    public DatosListaUsuario(Usuario datos) {
        this(
                datos.getNombre(),
                datos.getEmail(),
                datos.isActivo(),
                datos.getPerfiles().stream()
                        .map(Perfil::getNombre)
                        .collect(Collectors.toSet())
        );
    }
}
