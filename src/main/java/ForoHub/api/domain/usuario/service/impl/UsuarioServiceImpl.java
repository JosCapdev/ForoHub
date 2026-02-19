package ForoHub.api.domain.usuario.service.impl;

import ForoHub.api.domain.perfil.model.Perfil;
import ForoHub.api.domain.perfil.repository.PerfilRepository;
import ForoHub.api.domain.usuario.dto.DatosActualizarUsuario;
import ForoHub.api.domain.usuario.dto.DatosDetalleUsuario;
import ForoHub.api.domain.usuario.dto.DatosListaUsuario;
import ForoHub.api.domain.usuario.dto.DatosRegistroUsuario;
import ForoHub.api.domain.usuario.model.Usuario;
import ForoHub.api.domain.usuario.repository.UsuarioRepository;
import ForoHub.api.domain.usuario.service.UsuarioService;
import ForoHub.api.infra.exceptions.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PerfilRepository perfilRepository;

    @Transactional
    @Override
    public DatosDetalleUsuario registrarUsuario(DatosRegistroUsuario datos) {
        var passwordHash = passwordEncoder.encode(datos.pass());
        var usuario = new Usuario(datos.nombre(),datos.email(),passwordHash,true);
        Perfil perfilUser = perfilRepository.findByNombre("ESTUDIANTE").orElseThrow(()
                                -> new RuntimeException("Perfil ESTUDIANTE no existe"));
        usuario.getPerfiles().add(perfilUser);
        usuarioRepository.save(usuario);
        return new DatosDetalleUsuario(usuario);
    }

    @Override
    public Page<DatosListaUsuario> listarUsuarios(Pageable paginacion) {
        return usuarioRepository.findAll(paginacion).map(DatosListaUsuario::new);
    }

    @Transactional
    @Override
    public Usuario actualizarUsuario(Long id, DatosActualizarUsuario datos) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Usuario no encontrado"));

        if (datos.nombre().isBlank()){
            throw new ValidacionException("El nombre no puede estar vacio");
        }
        usuario.setNombre(datos.nombre());

        if (datos.pass().isBlank()){
            throw new ValidacionException("La contraseña no puede estar vacia");
        }
        usuario.setPass(passwordEncoder.encode(datos.pass()));

        if (datos.perfiles().isEmpty()){
                throw new ValidacionException("El perfil no puede estar vacio");
        }
        usuario.setPerfiles(datos.perfiles().stream()
                .map(nombrePerfil-> perfilRepository.findByNombre(nombrePerfil)
                        .orElseThrow(()->new RuntimeException("Perfil no encontrado:" + nombrePerfil)))
                .collect(Collectors.toSet()));
        return usuario;
    }

    @Transactional
    @Override
    public void eliminarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Usuario no encontrado"));

        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    @Override
    public DatosDetalleUsuario detallarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Usuario no encontrado"));

        return new DatosDetalleUsuario(usuario);
    }


}
