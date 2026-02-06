package ForoHub.api.controller;

import ForoHub.api.domain.topico.dto.DatosActualizarTopico;
import ForoHub.api.domain.topico.dto.DatosDetalleTopico;
import ForoHub.api.domain.topico.dto.DatosListaTopico;
import ForoHub.api.domain.topico.dto.DatosRegistroTopico;
import ForoHub.api.domain.topico.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosDetalleTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        var detalleTopico = topicoService.registrar(datos);
        URI uri = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(detalleTopico.id())
                .toUri();

        return ResponseEntity.created(uri).body(detalleTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(@PageableDefault(size = 10,sort = {"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion){
        var page = topicoService.listarTopicos(paginacion);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<DatosListaTopico>> buscarPorCursoYAnio(
            @RequestParam String nombreCurso,
            @RequestParam int anio,
            @PageableDefault(size = 10, sort = {"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion) {
        var page = topicoService.buscarPorCursoYAnio(nombreCurso, anio, paginacion);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> actualizarTopico(@PathVariable Long id , @RequestBody DatosActualizarTopico datos){
        var topicoActualizado = topicoService.actualizarTopico(id,datos);

        return  ResponseEntity.ok(new DatosDetalleTopico(topicoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoService.eliminarTopico(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico>detallarTopico(@PathVariable Long id){
        var detalleTopico = topicoService.DetallarTopico(id);

        return ResponseEntity.ok(detalleTopico);
    }

}
