package info.heitor.nutriflex;

import info.heitor.nutriflex.model.Categoria;
import info.heitor.nutriflex.model.Produto;
import info.heitor.nutriflex.service.CategoriaService;
import info.heitor.nutriflex.service.ProdutoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private CategoriaService categoriaService;

    @Test
    @DisplayName("Deve inserir um produtos")
    public void testInsert() {
        List<Produto> produtos = produtoService.listarTodosProdutos();
        int quantidade = produtos.size();
        Produto produto = new Produto();
        produto.setNome("Creatina");
        produto.setDescricao("Creatina da melhor qualidade com dawdawd");
        produto.setPreco(new BigDecimal("89.90"));
        produto.setEstoque(200);
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Suplemento");
        produto.setCategoria(categoria);
        produtoService.salvarProduto(produto);
        produtos = produtoService.listarTodosProdutos();
        int quantidadeFinal = produtos.size();
        assertEquals(quantidade + 1, quantidadeFinal);
    }

}
