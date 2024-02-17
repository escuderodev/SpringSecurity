package br.com.escuderodev.security.service;

import br.com.escuderodev.security.domain.usuario.DadosCadastroUsuario;
import br.com.escuderodev.security.domain.usuario.DadosListagemUsuario;
import br.com.escuderodev.security.domain.usuario.Usuario;
import br.com.escuderodev.security.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Page<DadosListagemUsuario> findAll(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        return repository.findAll(pagination).map(DadosListagemUsuario::new);
    }

    public Usuario findById(Long id) {
        return repository.getReferenceById(id);
    }

    public Usuario create(DadosCadastroUsuario dados) {
        var usuario = new Usuario(dados);
        return repository.save(usuario);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
