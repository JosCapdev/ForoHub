package ForoHub.api.domain.perfil.service;

import ForoHub.api.domain.perfil.dto.DatosActualizarPerfil;
import ForoHub.api.domain.perfil.dto.DatosDetallePerfil;
import ForoHub.api.domain.perfil.dto.DatosListaPerfil;
import ForoHub.api.domain.perfil.dto.DatosRegistroPerfil;
import ForoHub.api.domain.perfil.model.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PerfilService {

    DatosDetallePerfil registrarPerfil(DatosRegistroPerfil datos);

    Page<DatosListaPerfil> listarPerfiles(Pageable paginacion);

    Perfil actualizarPerfil(Long id, DatosActualizarPerfil datos);

    void eliminarPerfil(Long id);

    DatosDetallePerfil detallarPerfil(Long id);

}
