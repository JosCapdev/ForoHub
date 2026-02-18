package ForoHub.api.domain.curso.model;

import ForoHub.api.domain.curso.Categoria;
import ForoHub.api.domain.curso.dto.DatosRegistroCurso;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso(DatosRegistroCurso datos) {
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();
    }
}
