package ForoHub.api.domain.perfil.repository;

import ForoHub.api.domain.perfil.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil,Long> {
    Optional<Perfil> findByNombre(String nombre);
}

