package ForoHub.api.domain.respuesta;

import ForoHub.api.domain.topico.model.Topico;
import ForoHub.api.domain.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;
    private LocalDateTime fechaCreacion;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Topico topico;

    private boolean solucion;
}
