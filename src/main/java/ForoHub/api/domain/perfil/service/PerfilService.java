package ForoHub.api.domain.perfil.service;

import ForoHub.api.domain.perfil.model.Perfil;

import java.util.List;
import java.util.Optional;

public interface PerfilService {
    Optional<Perfil> buscarPorNombre(String roleUser);
    List<Perfil> listar();
}
