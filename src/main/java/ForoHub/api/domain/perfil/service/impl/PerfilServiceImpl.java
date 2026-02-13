package ForoHub.api.domain.perfil.service.impl;

import ForoHub.api.domain.perfil.model.Perfil;
import ForoHub.api.domain.perfil.repository.PerfilRepository;
import ForoHub.api.domain.perfil.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilServiceImpl implements PerfilService {
    @Autowired
    private PerfilRepository repository;

    @Override
    public Optional<Perfil> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public List<Perfil> listar() {
        return repository.findAll(); }
}
