package ForoHub.api.domain.topico.service;

import ForoHub.api.domain.topico.dto.DatosActualizarTopico;
import ForoHub.api.domain.topico.dto.DatosListaTopico;
import ForoHub.api.domain.topico.dto.DatosRegistroTopico;
import ForoHub.api.domain.topico.dto.DatosDetalleTopico;
import ForoHub.api.domain.topico.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TopicoService {

    DatosDetalleTopico registrarTopico(DatosRegistroTopico datos);

    Page<DatosListaTopico> listarTopicos(Pageable pageable);

    Page<DatosListaTopico> buscarTopicoPorCursoYAnio(String nombreCurso, int anio, Pageable paginacion);

    Topico actualizarTopico(Long id, DatosActualizarTopico datos);

    void eliminarTopico(Long id);

    DatosDetalleTopico DetallarTopico(Long id);
}
