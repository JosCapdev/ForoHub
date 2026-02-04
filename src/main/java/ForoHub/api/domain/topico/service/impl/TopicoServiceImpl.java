package ForoHub.api.domain.topico.service.impl;

import ForoHub.api.domain.curso.repository.CursoRepository;
import ForoHub.api.domain.topico.dto.DatosListaTopico;
import ForoHub.api.domain.topico.dto.DatosRegistroTopico;
import ForoHub.api.domain.topico.dto.DatosDetalleTopico;
import ForoHub.api.domain.topico.model.Topico;
import ForoHub.api.domain.topico.repository.TopicoRepository;
import ForoHub.api.domain.topico.service.TopicoService;
import ForoHub.api.domain.usuario.repository.UsuarioRepository;
import ForoHub.api.infra.errores.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TopicoServiceImpl implements TopicoService {
    @Autowired
    TopicoRepository repository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CursoRepository cursoRepository;
    public DatosDetalleTopico registrar(DatosRegistroTopico datos) {
        if (!usuarioRepository.existsById(datos.idUsuario())){
            throw new ValidacionException("No existe un usuario con el id: "+datos.idUsuario());
        }
        if (!cursoRepository.existsById(datos.idCurso())){
            throw new ValidacionException("No existe un curso con el id: "+datos.idCurso());
        }

        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y mensaje");
        }

        var autor = usuarioRepository.findById(datos.idUsuario()).get();
        var curso = cursoRepository.findById(datos.idCurso()).get();
        Topico topico = new Topico(datos);
        topico.setAutor(autor);
        topico.setCurso(curso);
        repository.save(topico);
        return new DatosDetalleTopico(topico);
    }

    @Override
    public Page<DatosListaTopico> listarTopicos(Pageable paginacion) {
        return repository.findAll(paginacion).map(DatosListaTopico::new);
    }

    @Override
    public Page<DatosListaTopico> buscarPorCursoYAnio(String nombreCurso, int anio, Pageable paginacion) {
        return repository.findByCursoNombreAndFechaCreacionYear(
                nombreCurso, anio, paginacion)
                .map(DatosListaTopico::new);
    }


}
