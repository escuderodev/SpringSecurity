package br.com.escuderodev.security.service;

import br.com.escuderodev.security.domain.produto.*;
import br.com.escuderodev.security.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    private ProdutoRepository repository;
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Page<DadosListagemProduto> findAll(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        return repository.findAll(pagination).map(DadosListagemProduto::new);
    }

    public Produto findById(Long id) {
        return repository.getReferenceById(id);
    }

    public Produto create(DadosCadastroProduto dados) {
        var produto = new Produto(dados);
        return repository.save(produto);
    }

    public Produto update(DadosAtualizaProduto dados) {
        var produto = repository.getReferenceById(dados.id());
        produto.atualizaDados(dados);
        return produto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
