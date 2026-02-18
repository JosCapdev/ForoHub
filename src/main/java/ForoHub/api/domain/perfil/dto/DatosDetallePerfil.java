package ForoHub.api.domain.perfil.dto;

import ForoHub.api.domain.perfil.model.Perfil;

public record DatosDetallePerfil(Long id, String nombre) {

    public DatosDetallePerfil(Perfil perfil) {
        this(perfil.getId(),perfil.getNombre());
    }
}
