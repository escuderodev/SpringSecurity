package br.com.escuderodev.security.repository;

import br.com.escuderodev.security.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
