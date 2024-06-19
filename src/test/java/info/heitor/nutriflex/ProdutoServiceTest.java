package info.heitor.nutriflex;

import info.heitor.nutriflex.model.AcaoHistorico;
import info.heitor.nutriflex.model.Categoria;
import info.heitor.nutriflex.model.HistoricoProduto;
import info.heitor.nutriflex.model.Produto;
import info.heitor.nutriflex.repository.HistoricoProdutoRepository;
import info.heitor.nutriflex.repository.ProdutoRepository;
import info.heitor.nutriflex.service.CategoriaService;
import info.heitor.nutriflex.service.ProdutoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private HistoricoProdutoRepository historicoProdutoRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Test
    @DisplayName("Deve inserir um produto e registrar histórico")
    public void testSalvarProduto() {
        Categoria categoria = new Categoria();
        categoria.setNome("Suplementos");
        categoriaService.salvarCategoria(categoria);

        Produto produto = new Produto();
        produto.setNome("Creatina");
        produto.setDescricao("Creatina com a melhor qualidade do mercado");
        produto.setPreco(new BigDecimal("89.90"));
        produto.setEstoque(200);
        produto.setCategoria(categoria);

        produtoService.salvarProduto(produto);

        Produto produtoSalvo = produtoRepository.findById(produto.getId()).orElse(null);
        assertNotNull(produtoSalvo);
        assertEquals("Creatina", produtoSalvo.getNome());

        List<HistoricoProduto> historicos = historicoProdutoRepository.findAll();
        assertFalse(historicos.isEmpty());
        HistoricoProduto historicoSalvo = historicos.get(0);
        assertEquals(produtoSalvo, historicoSalvo.getProduto());
        assertEquals(AcaoHistorico.CRIACAO, historicoSalvo.getAcao());
    }

    @Test
    @DisplayName("Deve encontrar um produto por ID e registrar histórico de consulta")
    public void testEncontrarProdutoPorId() {
        Categoria categoria = new Categoria();
        categoria.setNome("Suplementos");
        categoriaService.salvarCategoria(categoria);

        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setCategoria(categoria);
        produtoRepository.save(produto);

        Optional<Produto> produtoEncontrado = produtoService.encontrarProdutoPorId(produto.getId());

        assertTrue(produtoEncontrado.isPresent());
        assertEquals("Produto Teste", produtoEncontrado.get().getNome());

        List<HistoricoProduto> historicos = historicoProdutoRepository.findAll();
        assertFalse(historicos.isEmpty());
        HistoricoProduto historicoSalvo = historicos.get(0);
        assertEquals(produto, historicoSalvo.getProduto());
        assertEquals(AcaoHistorico.CONSULTA, historicoSalvo.getAcao());
    }

    @Test
    @DisplayName("Deve deletar um produto por ID e registrar histórico de exclusão")
    public void testDeletarProdutoPorId() {
        Categoria categoria = new Categoria();
        categoria.setNome("Suplementos");
        categoriaService.salvarCategoria(categoria);

        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setCategoria(categoria);
        produtoRepository.save(produto);

        produtoService.deletarProdutoPorId(produto.getId());

        Optional<Produto> produtoDeletado = produtoRepository.findById(produto.getId());
        assertTrue(produtoDeletado.isEmpty());

        List<HistoricoProduto> historicos = historicoProdutoRepository.findAll();
        assertFalse(historicos.isEmpty());
        HistoricoProduto historicoSalvo = historicos.get(0);
        assertEquals(produto, historicoSalvo.getProduto());
        assertEquals(AcaoHistorico.EXCLUSAO, historicoSalvo.getAcao());
    }

    @Test
    @DisplayName("Deve atualizar um produto e registrar histórico de atualização")
    public void testAtualizarProduto() {
        Categoria categoria = new Categoria();
        categoria.setNome("Suplementos");
        categoriaService.salvarCategoria(categoria);

        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setCategoria(categoria);
        produtoRepository.save(produto);

        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setNome("Produto Atualizado");
        produtoAtualizado.setPreco(BigDecimal.valueOf(20.0));
        produtoAtualizado.setEstoque(50);
        produtoAtualizado.setCategoria(categoria);

        produtoService.atualizarProduto(produto.getId(), produtoAtualizado);

        Produto produtoSalvo = produtoRepository.findById(produto.getId()).orElse(null);
        assertNotNull(produtoSalvo);
        assertEquals("Produto Atualizado", produtoSalvo.getNome());

        List<HistoricoProduto> historicos = historicoProdutoRepository.findAll();
        assertFalse(historicos.isEmpty());
        HistoricoProduto historicoSalvo = historicos.get(0);
        assertEquals(produtoSalvo, historicoSalvo.getProduto());
        assertEquals(AcaoHistorico.ATUALIZACAO, historicoSalvo.getAcao());
    }
}
