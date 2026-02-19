package ForoHub.api.domain.curso.repository;

import ForoHub.api.domain.curso.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {
    Page<Curso> findByActivoTrue(Pageable pageable);
    Optional<Curso> findByIdAndActivoTrue(Long id);
}
