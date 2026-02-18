package ForoHub.api.domain.usuario.service;

import ForoHub.api.domain.usuario.dto.DatosActualizarUsuario;
import ForoHub.api.domain.usuario.dto.DatosDetalleUsuario;
import ForoHub.api.domain.usuario.dto.DatosListaUsuario;
import ForoHub.api.domain.usuario.dto.DatosRegistroUsuario;
import ForoHub.api.domain.usuario.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    DatosDetalleUsuario registrarUsuario(DatosRegistroUsuario datos);

    Page<DatosListaUsuario> listarUsuarios(Pageable paginacion);

    Usuario actualizarUsuario(Long id, DatosActualizarUsuario datos);

    void eliminarUsuario(Long id);

    DatosDetalleUsuario detallarUsuario(Long id);
}
