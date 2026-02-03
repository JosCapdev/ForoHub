package ForoHub.api.domain.topico.repository;

import ForoHub.api.domain.topico.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico,Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
