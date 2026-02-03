package ForoHub.api.domain.topico.service;

import ForoHub.api.domain.topico.dto.DatosRegistroTopico;
import ForoHub.api.domain.topico.dto.DatosDetalleTopico;

public interface TopicoService {

    DatosDetalleTopico registrar(DatosRegistroTopico datos);

}
