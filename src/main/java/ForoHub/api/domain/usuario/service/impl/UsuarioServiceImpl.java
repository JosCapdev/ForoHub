package ForoHub.api.domain.usuario.service.impl;

import ForoHub.api.domain.perfil.model.Perfil;
import ForoHub.api.domain.perfil.service.PerfilService;
import ForoHub.api.domain.usuario.dto.DatosDetalleUsuario;
import ForoHub.api.domain.usuario.dto.DatosRegistroUsuario;
import ForoHub.api.domain.usuario.model.Usuario;
import ForoHub.api.domain.usuario.repository.UsuarioRepository;
import ForoHub.api.domain.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PerfilService perfilService;

    @Transactional
    @Override
    public DatosDetalleUsuario registrar(DatosRegistroUsuario datos) {
        var passwordHash = passwordEncoder.encode(datos.pass());
        var usuario = new Usuario(datos.nombre(),datos.email(),passwordHash,true);
        Perfil perfilUser = perfilService.buscarPorNombre("ESTUDIANTE").orElseThrow(()
                                -> new RuntimeException("Perfil ESTUDIANTE no existe"));
        usuario.getPerfiles().add(perfilUser);
        repository.save(usuario);
        return new DatosDetalleUsuario(usuario);
    }
}
