package ForoHub.api.domain.perfil.service.impl;

import ForoHub.api.domain.perfil.dto.DatosActualizarPerfil;
import ForoHub.api.domain.perfil.dto.DatosDetallePerfil;
import ForoHub.api.domain.perfil.dto.DatosRegistroPerfil;
import ForoHub.api.domain.perfil.model.Perfil;
import ForoHub.api.domain.perfil.repository.PerfilRepository;
import ForoHub.api.domain.perfil.dto.DatosListaPerfil;
import ForoHub.api.domain.perfil.service.PerfilService;
import ForoHub.api.infra.exceptions.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilServiceImpl implements PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;

    @Transactional
    @Override
    public DatosDetallePerfil registrarPerfil(DatosRegistroPerfil datos) {
        var perfil = new Perfil(datos);
        perfilRepository.save(perfil);
        return new DatosDetallePerfil(perfil);
    }

    @Override
    public Page<DatosListaPerfil> listarPerfiles(Pageable paginacion) {
        return perfilRepository.findAll(paginacion)
                .map(DatosListaPerfil::new);
    }

    @Transactional
    @Override
    public Perfil actualizarPerfil(Long id, DatosActualizarPerfil datos) {
        var perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Perfil no encontrado"));
        if (datos.nombre().isBlank()){
            throw new ValidacionException("El nombre no puede estar vacio");
        }
        perfil.setNombre(datos.nombre());
        perfilRepository.save(perfil);

        return perfil;
    }

    @Transactional
    @Override
    public void eliminarPerfil(Long id) {
        if (perfilRepository.existsById(id)){
            perfilRepository.deleteById(id);
        }else{
            throw new ValidacionException("Perfil no encontrado");
        }
    }

    @Override
    public DatosDetallePerfil detallarPerfil(Long id) {
        var perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Perfil no encontrado"));
        return new DatosDetallePerfil(perfil);
    }
}
