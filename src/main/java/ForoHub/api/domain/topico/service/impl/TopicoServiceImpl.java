package ForoHub.api.domain.topico.service.impl;

import ForoHub.api.domain.curso.model.Curso;
import ForoHub.api.domain.curso.repository.CursoRepository;
import ForoHub.api.domain.topico.dto.DatosActualizarTopico;
import ForoHub.api.domain.topico.dto.DatosListaTopico;
import ForoHub.api.domain.topico.dto.DatosRegistroTopico;
import ForoHub.api.domain.topico.dto.DatosDetalleTopico;
import ForoHub.api.domain.topico.model.Topico;
import ForoHub.api.domain.topico.repository.TopicoRepository;
import ForoHub.api.domain.topico.service.TopicoService;
import ForoHub.api.domain.usuario.repository.UsuarioRepository;
import ForoHub.api.infra.exceptions.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class TopicoServiceImpl implements TopicoService {
    @Autowired
    TopicoRepository repository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CursoRepository cursoRepository;

    @Transactional
    @Override
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
        var topico = new Topico(datos);
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

    @Transactional
    @Override
    public Topico actualizarTopico(Long id, DatosActualizarTopico datos) {
        // Verificando con el método sugerido isPresent() de la clase Java llamada Optional
        Optional<Topico> optionalTopico = repository.findById(id);
        if (!optionalTopico.isPresent()) {
            throw new ValidacionException("Tópico no encontrado");
        }
        Topico topico = optionalTopico.get();

        if (datos.titulo() != null) {
            if (datos.titulo().isBlank()) {
                throw new ValidacionException("El título no puede estar vacío");
            }
            topico.setTitulo(datos.titulo());
        }

        if (datos.mensaje() != null) {
            if (datos.mensaje().isBlank()) {
                throw new ValidacionException("El mensaje no puede estar vacío");
            }
            topico.setMensaje(datos.mensaje());
        }

        if (datos.idCurso() != null) {
            // En este caso utilizo orElseThrow() para obtener el curso
            Curso curso = cursoRepository.findById(datos.idCurso())
                    .orElseThrow(() -> new ValidacionException("Curso no encontrado"));
            topico.setCurso(curso);
        }

        return topico;

    }

    @Transactional
    @Override
    public void eliminarTopico(Long id) {
        Optional<Topico> topico = repository.findById(id);
        if (!topico.isPresent()){
            throw new ValidacionException("Topico no encontrado con id: "+id);
        }
        repository.deleteById(id);
    }

    @Override
    public DatosDetalleTopico DetallarTopico(Long id) {
        Optional<Topico> topico = repository.findById(id);
        if (!topico.isPresent()){
            throw new ValidacionException("Topico no encontrado con id: "+id);
        }
        return new DatosDetalleTopico(topico.get());
    }

}
