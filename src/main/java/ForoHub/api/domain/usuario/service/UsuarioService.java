package ForoHub.api.domain.usuario.service;

import ForoHub.api.domain.usuario.dto.DatosDetalleUsuario;
import ForoHub.api.domain.usuario.dto.DatosRegistroUsuario;

public interface UsuarioService {
    DatosDetalleUsuario registrar(DatosRegistroUsuario datos);
}
