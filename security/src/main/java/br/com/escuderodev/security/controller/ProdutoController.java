package br.com.escuderodev.security.controller;

import br.com.escuderodev.security.domain.produto.DadosAtualizaProduto;
import br.com.escuderodev.security.domain.produto.DadosCadastroProduto;
import br.com.escuderodev.security.domain.produto.DadosListagemProduto;
import br.com.escuderodev.security.service.ProdutoService;
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
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> findAll(@PageableDefault(size = 10,page = 0,sort = {"id"}) Pageable pagination) {
        var page = service.findAll(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        var produto = service.findById(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriBuilder) {
        var produto = service.create(dados);
        var uri = uriBuilder.path("produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DadosAtualizaProduto dados) {
        service.update(dados);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
