package info.heitor.nutriflex;

import info.heitor.nutriflex.Filters.ProdutoFiltros;
import info.heitor.nutriflex.model.AcaoHistorico;
import info.heitor.nutriflex.model.Categoria;
import info.heitor.nutriflex.model.HistoricoProduto;
import info.heitor.nutriflex.model.Produto;
import info.heitor.nutriflex.service.HistoricoProdutoService;
import info.heitor.nutriflex.service.ProdutoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private HistoricoProdutoService historicoProdutoService;


    @Test
    public void testBuscarFiltros() {
        // Criar filtros simulados
        ProdutoFiltros filtros = new ProdutoFiltros();
        filtros.setNome(Optional.of("Whey"));  // Exemplo de filtro por nome
        //filtros.setCategoria(...);  // Pode adicionar filtro por categoria se necessário

        // Chamar o método buscarFiltros
        List<Produto> produtos = produtoService.buscarFiltros(filtros);

        // Verificar se a lista de produtos não está vazia e se contém o produto esperado
        // Aqui, você deve ajustar a asserção de acordo com o comportamento esperado do método
        Assertions.assertFalse(produtos.isEmpty(), "A lista de produtos não deve estar vazia");
        Assertions.assertTrue(produtos.stream().anyMatch(p -> p.getNome().contains("Whey")),
                "Deveria encontrar pelo menos um produto com o nome 'Whey'");
    }

    @Test
    @DisplayName("Deve salvar um produto")
    @Rollback
    @Transactional
    public void testSalvarProduto() {
        Produto produto = new Produto();
        produto.setNome("Creatina");
        produto.setDescricao("Creatina com a melhor qualidade do mercado");
        produto.setPreco(new BigDecimal("89.90"));
        produto.setEstoque(200);
        produto.setCategoria(new Categoria(1L, "Suplementos"));

        Produto produtoSalvo = produtoService.salvarProduto(produto);

        assertNotNull(produtoSalvo.getId());
        assertEquals("Creatina", produtoSalvo.getNome());
        assertFalse(produtoSalvo.getCodigo().isEmpty());

        // Verifica o histórico
        List<HistoricoProduto> historicos = historicoProdutoService.buscarTodos();
        assertEquals(4, historicos.size());
        assertEquals(AcaoHistorico.CRIACAO, historicos.get(0).getAcao());
    }

    @Test
    @DisplayName("Deve listar todos os produtos")
    public void testListarTodosProdutos() {
        List<Produto> produtos = produtoService.listarTodosProdutos();
        assertNotNull(produtos);
        assertFalse(produtos.isEmpty());
    }

    @Test
    @DisplayName("Deve encontrar um produto por ID")
    public void testEncontrarProdutoPorId() {
        Optional<Produto> produto = produtoService.encontrarProdutoPorId(1L);
        assertTrue(produto.isPresent());

        // Verifica o histórico
        List<HistoricoProduto> historicos = historicoProdutoService.buscarTodos();
        assertEquals(4, historicos.size());
        assertEquals(AcaoHistorico.CONSULTA, historicos.get(3).getAcao());
    }

    @Test
    @DisplayName("Deve deletar um produto por ID")
    @Transactional
    public void testDeletarProdutoPorId() {
        produtoService.deletarProdutoPorId(1L);
        Optional<Produto> produto = produtoService.encontrarProdutoPorId(1L);
        assertFalse(produto.isPresent());

        // Verifica o histórico
        List<HistoricoProduto> historicos = historicoProdutoService.buscarTodos();
        assertEquals(5, historicos.size());
        assertEquals(AcaoHistorico.EXCLUSAO, historicos.get(4).getAcao());
    }

    @Test
    @DisplayName("Deve atualizar um produto")
    @Transactional
    public void testAtualizarProduto() {
        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setNome("Creatina Atualizada");
        produtoAtualizado.setDescricao("Creatina com a melhor qualidade do mercado - Atualizada");
        produtoAtualizado.setPreco(new BigDecimal("99.90"));
        produtoAtualizado.setEstoque(150);
        produtoAtualizado.setCategoria(new Categoria(1L, "Suplementos"));

        Produto produto = produtoService.atualizarProduto(1L, produtoAtualizado);

        assertEquals("Creatina Atualizada", produto.getNome());
        assertEquals(new BigDecimal("99.90"), produto.getPreco());

        // Verifica o histórico
        List<HistoricoProduto> historicos = historicoProdutoService.buscarTodos();
        assertEquals(5, historicos.size());
        assertEquals(AcaoHistorico.ATUALIZACAO, historicos.get(4).getAcao());
    }
}
