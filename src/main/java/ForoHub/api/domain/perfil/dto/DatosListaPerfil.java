package ForoHub.api.domain.perfil.dto;

import ForoHub.api.domain.perfil.model.Perfil;

public record DatosListaPerfil(String nombre) {

    public DatosListaPerfil(Perfil datos) {
        this(datos.getNombre());
    }
}
