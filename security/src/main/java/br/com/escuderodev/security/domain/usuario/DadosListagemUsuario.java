package br.com.escuderodev.security.domain.usuario;

public record DadosListagemUsuario(
        Long id,
        String login,
        Role role
) {
    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername(), usuario.getRole());
    }
}
