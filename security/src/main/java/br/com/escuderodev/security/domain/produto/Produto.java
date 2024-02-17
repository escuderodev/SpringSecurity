package br.com.escuderodev.security.domain.produto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;

@Entity(name = "Produto")
@Table(name = "produtos")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;

    public Produto(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.preco = dados.preco();
    }

    public Produto() {
    }

    public void atualizaDados(DadosAtualizaProduto dados) {
        this.nome = dados.nome();
        this.preco = dados.preco();
    }
}
