package br.com.escuderodev.security.repository;

import br.com.escuderodev.security.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
