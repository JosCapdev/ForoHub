package ForoHub.api.controller;

import ForoHub.api.domain.usuario.dto.DatosDetalleUsuario;
import ForoHub.api.domain.usuario.dto.DatosRegistroUsuario;
import ForoHub.api.domain.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService service;

    @PostMapping
    public ResponseEntity<DatosDetalleUsuario> registrar(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder){
        var detalleUsuario = service.registrar(datos);
        URI uri = uriComponentsBuilder.path("/usuario/{id}")
                .buildAndExpand(detalleUsuario.id())
                .toUri();
        return ResponseEntity.created(uri).body(detalleUsuario);
    }


}
