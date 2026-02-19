package ForoHub.api.domain.topico.repository;

import ForoHub.api.domain.topico.StatusTopico;
import ForoHub.api.domain.topico.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico,Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    Optional<Topico> findByIdAndStatusNot(Long id, StatusTopico status);

    Page<Topico> findByStatusNot(StatusTopico status, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.curso.nombre = :nombreCurso " +
            "AND YEAR(t.fechaCreacion) = :anio AND t.status <> :statusCerrado")
    Page<Topico> findByCursoNombreAndFechaCreacionYearAndStatusNot(
            @Param("nombreCurso") String nombreCurso,
            @Param("anio") int anio,
            @Param("statusCerrado") StatusTopico statusCerrado,
            Pageable pageable);

}
