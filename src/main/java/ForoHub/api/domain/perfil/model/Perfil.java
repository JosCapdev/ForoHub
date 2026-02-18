package ForoHub.api.domain.perfil.model;

import ForoHub.api.domain.perfil.dto.DatosRegistroPerfil;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "perfiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nombre;

    public Perfil(DatosRegistroPerfil datos) {
        this.nombre = datos.nombre();
    }
}
