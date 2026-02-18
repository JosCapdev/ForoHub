package ForoHub.api.controller;

import ForoHub.api.domain.respuesta.dto.DatosDetalleRespuesta;
import ForoHub.api.domain.respuesta.dto.DatosListaRespuesta;
import ForoHub.api.domain.respuesta.dto.DatosRegistroRespuesta;
import ForoHub.api.domain.respuesta.service.RespuestaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    @Autowired
    RespuestaService service;

    @PostMapping
    public ResponseEntity<DatosDetalleRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos, UriComponentsBuilder uriComponentsBuilder){
        var detalleRespuesta = service.registrarRespuesta(datos);
        URI uri = uriComponentsBuilder.path("/respuestas/{id}")
                .buildAndExpand(detalleRespuesta.id())
                .toUri();
        return ResponseEntity.created(uri).body(detalleRespuesta);
    }
    @GetMapping("/topicos/{id}")
    public ResponseEntity<Page<DatosListaRespuesta>> listarRespuestaPorTopico(
            @PathVariable Long id,
            @PageableDefault(size = 10,sort = {"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion){
        var page = service.listarRespuestaPorTopico(id,paginacion);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        service.eliminarRespuesta(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleRespuesta>detallarRespuesta(@PathVariable Long id){
        var detalleRespuesta = service.detallarRespuesta(id);

        return ResponseEntity.ok(detalleRespuesta);
    }
}
