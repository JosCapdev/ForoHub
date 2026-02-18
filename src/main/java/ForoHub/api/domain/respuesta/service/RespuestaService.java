package ForoHub.api.domain.respuesta.service;

import ForoHub.api.domain.respuesta.dto.DatosDetalleRespuesta;
import ForoHub.api.domain.respuesta.dto.DatosListaRespuesta;
import ForoHub.api.domain.respuesta.dto.DatosRegistroRespuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RespuestaService {
    DatosDetalleRespuesta registrarRespuesta(DatosRegistroRespuesta datos);

    Page<DatosListaRespuesta> listarRespuestaPorTopico(Long idTopico,Pageable paginacion);

    void eliminarRespuesta(Long id);

    DatosDetalleRespuesta detallarRespuesta(Long id);
}
