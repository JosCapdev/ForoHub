package ForoHub.api.domain.respuesta.repository;

import ForoHub.api.domain.respuesta.model.Respuesta;
import ForoHub.api.domain.topico.StatusTopico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta,Long> {
    Page<Respuesta> findAllByTopicoIdAndTopicoStatusNot(Long idTopico, StatusTopico status, Pageable paginacion);
}
