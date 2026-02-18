package ForoHub.api.controller;

import ForoHub.api.domain.usuario.dto.DatosActualizarUsuario;
import ForoHub.api.domain.usuario.dto.DatosDetalleUsuario;
import ForoHub.api.domain.usuario.dto.DatosListaUsuario;
import ForoHub.api.domain.usuario.dto.DatosRegistroUsuario;
import ForoHub.api.domain.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    UsuarioService service;

    @Operation(security = {})
    @PostMapping
    public ResponseEntity<DatosDetalleUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder){
        var detalleUsuario = service.registrarUsuario(datos);
        URI uri = uriComponentsBuilder.path("/usuario/{id}")
                .buildAndExpand(detalleUsuario.id())
                .toUri();
        return ResponseEntity.created(uri).body(detalleUsuario);
    }
    @GetMapping
    public ResponseEntity<Page<DatosListaUsuario>> listarUsuarios(@PageableDefault(size = 10,sort = {"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion){
        var page = service.listarUsuarios(paginacion);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleUsuario> actualizarUsuario(@PathVariable Long id , @RequestBody DatosActualizarUsuario datos){
        var usuarioActualizado = service.actualizarUsuario(id,datos);

        return  ResponseEntity.ok(new DatosDetalleUsuario(usuarioActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        service.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleUsuario>detallarUsuario(@PathVariable Long id){
        var detalleUsuario = service.detallarUsuario(id);

        return ResponseEntity.ok(detalleUsuario);
    }

}
