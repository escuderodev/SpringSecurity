package br.com.escuderodev.security.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaUsuario(
        @NotNull
        Long id,
        @NotBlank
        String login,
        @NotNull
        String password,
        @NotNull
        Role role
) {
}
