package info.heitor.nutriflex.service;

import info.heitor.nutriflex.model.Produto;
import info.heitor.nutriflex.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto salvarProduto(Produto produto) {
        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    @Override
    public Optional<Produto> encontrarProdutoPorId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }
        return produtoRepository.findById(id);
    }

    @Override
    public void deletarProdutoPorId(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new NoSuchElementException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }
        validarProduto(produtoAtualizado);
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Produto de ID: " + id + " não encontrado"));
        produtoAtualizado.setId(id);
        return produtoRepository.save(produtoAtualizado);
    }

    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode estar em branco");
        }
        if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço do produto deve ser maior que zero");
        }
        if (produto.getEstoque() < 0) {
            throw new IllegalArgumentException("Estoque do produto não pode ser negativo");
        }
    }
}
