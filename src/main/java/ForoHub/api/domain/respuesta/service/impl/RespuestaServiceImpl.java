package ForoHub.api.domain.respuesta.service.impl;

import ForoHub.api.domain.respuesta.dto.DatosDetalleRespuesta;
import ForoHub.api.domain.respuesta.dto.DatosListaRespuesta;
import ForoHub.api.domain.respuesta.dto.DatosRegistroRespuesta;
import ForoHub.api.domain.respuesta.model.Respuesta;
import ForoHub.api.domain.respuesta.repository.RespuestaRepository;
import ForoHub.api.domain.respuesta.service.RespuestaService;
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
        if (!usuarioRepository.existsById(datos.idUsuario())){
            throw new ValidacionException("No existe un usuario con el id: "+datos.idUsuario());
        }
        if (!topicoRepository.existsById(datos.idTopico())){
            throw new ValidacionException("No existe un topico con el id: "+datos.idTopico());
        }
        var autor = usuarioRepository.findById(datos.idUsuario()).get();
        var topico = topicoRepository.findById(datos.idTopico()).get();
        var respuesta = new Respuesta(datos);
        respuesta.setAutor(autor);
        respuesta.setTopico(topico);
        respuestaRepository.save(respuesta);
        return new DatosDetalleRespuesta(respuesta);
    }

    @Override
    public Page<DatosListaRespuesta> listarRespuestaPorTopico(Long idTopico,Pageable paginacion) {
        return respuestaRepository.findAllByTopicoId(idTopico,paginacion).map(DatosListaRespuesta::new);
    }

    @Override
    public void eliminarRespuesta(Long id) {
        if (respuestaRepository.existsById(id)){
            respuestaRepository.deleteById(id);
        }else{
            throw new ValidacionException("Respuesta no encontrado");
        }
    }

    @Override
    public DatosDetalleRespuesta detallarRespuesta(Long id) {
        var respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Respuesta no encontrada"));

        return new DatosDetalleRespuesta(respuesta);
    }
}
