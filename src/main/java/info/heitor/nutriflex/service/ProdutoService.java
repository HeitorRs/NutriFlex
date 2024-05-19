package info.heitor.nutriflex.service;

import info.heitor.nutriflex.model.Produto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    public Produto salvar(Produto produto) {
        if (produto.getId() == null || produto.getId() <= 0) {
            throw new IllegalArgumentException("id inválido!");
        }
        validarProduto(produto);
        produtos.add(produto);
        return produto;
    }

    public Produto encontrarPorId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id inválido!");
        }
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new NoSuchElementException("Produto de id:" + id + " não encontrado"));
    }

    public void deletarPorId(Long id) {
        if (!produtos.removeIf(p -> p.getId().equals(id))) {
            throw new NoSuchElementException("Produto não encontrado");
        }
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id inválido!");
        }
        validarProduto(produtoAtualizado);
        for (int i = 0; i < produtos.size(); i++) {
            Produto produtoExistente = produtos.get(i);
            if (produtoExistente.getId().equals(id)) {
                produtoAtualizado.setId(id);
                produtos.set(i, produtoAtualizado);
                return produtoAtualizado;
            }
        }
        throw new NoSuchElementException("Produto de id:" + id + " não encontrado");
    }

    public void validarProduto(Produto produto) {
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
