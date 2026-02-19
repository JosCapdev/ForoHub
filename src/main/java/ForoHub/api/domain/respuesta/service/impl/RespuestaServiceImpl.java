package ForoHub.api.domain.respuesta.service.impl;

import ForoHub.api.domain.respuesta.dto.DatosDetalleRespuesta;
import ForoHub.api.domain.respuesta.dto.DatosListaRespuesta;
import ForoHub.api.domain.respuesta.dto.DatosRegistroRespuesta;
import ForoHub.api.domain.respuesta.model.Respuesta;
import ForoHub.api.domain.respuesta.repository.RespuestaRepository;
import ForoHub.api.domain.respuesta.service.RespuestaService;
import ForoHub.api.domain.topico.StatusTopico;
import ForoHub.api.domain.topico.repository.TopicoRepository;
import ForoHub.api.domain.usuario.repository.UsuarioRepository;
import ForoHub.api.infra.exceptions.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RespuestaServiceImpl implements RespuestaService {
    @Autowired
    RespuestaRepository respuestaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TopicoRepository topicoRepository;

    @Override
    public DatosDetalleRespuesta registrarRespuesta(DatosRegistroRespuesta datos) {
        var autor = usuarioRepository.findByIdAndActivoTrue(datos.idUsuario())
                .orElseThrow(() -> new ValidacionException("No existe un usuario activo con el id: " + datos.idUsuario()));

        var topico = topicoRepository.findByIdAndStatusNot(datos.idTopico(), StatusTopico.CERRADO)
                .orElseThrow(() -> new ValidacionException("No existe un tópico activo con el id: " + datos.idTopico()));

        var respuesta = new Respuesta(datos);
        respuesta.setAutor(autor);
        respuesta.setTopico(topico);
        respuestaRepository.save(respuesta);
        return new DatosDetalleRespuesta(respuesta);
    }

    @Override
    public Page<DatosListaRespuesta> listarRespuestaPorTopico(Long idTopico,Pageable paginacion) {
        return respuestaRepository.findAllByTopicoIdAndTopicoStatusNot(idTopico, StatusTopico.CERRADO, paginacion).map(DatosListaRespuesta::new);
    }

    @Override
    public void eliminarRespuesta(Long id) {
        if (respuestaRepository.existsById(id)){
            respuestaRepository.deleteById(id);
        }else{
            throw new ValidacionException("Respuesta no encontrada");
        }
    }

    @Override
    public DatosDetalleRespuesta detallarRespuesta(Long id) {
        var respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Respuesta no encontrada"));

        return new DatosDetalleRespuesta(respuesta);
    }
}
