package ForoHub.api.domain.usuario.repository;

import ForoHub.api.domain.usuario.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByEmailAndActivoTrue(String email);

    Optional<Usuario> findByIdAndActivoTrue(Long id);

    Page<Usuario> findByActivoTrue(Pageable paginacion);
}
