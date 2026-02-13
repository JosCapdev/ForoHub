package ForoHub.api.infra.config;

import ForoHub.api.domain.perfil.model.Perfil;
import ForoHub.api.domain.perfil.repository.PerfilRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initPerfiles(PerfilRepository perfilRepository) {
        return args -> {
            List<String> rolesEnElSistema = List.of("ESTUDIANTE", "MODERADOR", "ADMIN", "INSTRUCTOR");

            rolesEnElSistema.forEach(nombrePerfil -> {
                if (perfilRepository.findByNombre(nombrePerfil).isEmpty()) {
                    perfilRepository.save(new Perfil(null, nombrePerfil));
                    System.out.println("Perfil creado: " + nombrePerfil);
                }
            });
        };
    }
}
