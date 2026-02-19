package ForoHub.api.controller;

import ForoHub.api.domain.perfil.dto.DatosActualizarPerfil;
import ForoHub.api.domain.perfil.dto.DatosDetallePerfil;
import ForoHub.api.domain.perfil.dto.DatosListaPerfil;
import ForoHub.api.domain.perfil.dto.DatosRegistroPerfil;
import ForoHub.api.domain.perfil.service.PerfilService;
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
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {
    @Autowired
    PerfilService service;

    @PostMapping
    public ResponseEntity<DatosDetallePerfil> registrarPerfil(@RequestBody @Valid DatosRegistroPerfil datos, UriComponentsBuilder uriComponentsBuilder){
        var detallePerfil = service.registrarPerfil(datos);
        URI uri = uriComponentsBuilder.path("/perfiles/{id}")
                .buildAndExpand(detallePerfil.id())
                .toUri();
        return ResponseEntity.created(uri).body(detallePerfil);
    }
    @GetMapping
    public ResponseEntity<Page<DatosListaPerfil>> listarPerfiles(@PageableDefault(size = 10,sort = {"nombre"}, direction = Sort.Direction.ASC) Pageable paginacion){
        var page = service.listarPerfiles(paginacion);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDetallePerfil> actualizarPerfil(@PathVariable Long id , @RequestBody DatosActualizarPerfil datos){
        var perfilActualizado = service.actualizarPerfil(id,datos);

        return  ResponseEntity.ok(new DatosDetallePerfil(perfilActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPerfil(@PathVariable Long id){
        service.eliminarPerfil(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallePerfil>detallarPerfil(@PathVariable Long id){
        var detallePerfil = service.detallarPerfil(id);

        return ResponseEntity.ok(detallePerfil);
    }
}
