package ForoHub.api.domain.usuario.service.impl;

import ForoHub.api.domain.perfil.model.Perfil;
import ForoHub.api.domain.perfil.service.PerfilService;
import ForoHub.api.domain.usuario.dto.DatosDetalleUsuario;
import ForoHub.api.domain.usuario.dto.DatosRegistroUsuario;
import ForoHub.api.domain.usuario.model.Usuario;
import ForoHub.api.domain.usuario.repository.UsuarioRepository;
import ForoHub.api.domain.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository repository;
    @Autowired
    PerfilService perfilService;

    @Transactional
    @Override
    public DatosDetalleUsuario registrar(DatosRegistroUsuario datos) {
        var usuario = new Usuario(datos);
        Perfil perfilUser = perfilService.buscarPorNombre("ROLE_USER").orElseThrow(()
                                -> new RuntimeException("Perfil ROLE_USER no existe"));
        usuario.getPerfiles().add(perfilUser);
        repository.save(usuario);
        return new DatosDetalleUsuario(usuario);
    }
}
