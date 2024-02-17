package br.com.escuderodev.security.controller;

import br.com.escuderodev.security.domain.usuario.DadosCadastroUsuario;
import br.com.escuderodev.security.domain.usuario.DadosListagemUsuario;
import br.com.escuderodev.security.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> findAll(@PageableDefault(size = 10,page = 0,sort = {"id"}) Pageable pagination) {
        var page = service.findAll(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        var usuario = service.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuario = service.create(dados);
        var uri = uriBuilder.path("produto/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
