package info.heitor.nutriflex.service;

import info.heitor.nutriflex.Filters.ProdutoFiltros;
import info.heitor.nutriflex.model.Produto;
import info.heitor.nutriflex.repository.ProdutoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository produtoRepository;
    private final EntityManager entityManager;

    @Override
    public List<Produto> buscarFiltros(ProdutoFiltros filtros) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> cq = cb.createQuery(Produto.class);
        Root<Produto> produto = cq.from(Produto.class);
        List<Predicate> predicates = new ArrayList<>();

        if(filtros.getNome().isPresent()) {
            String query = filtros.getNome().get() + "%";
            Predicate nome = cb.like(produto.get("nome"),query);
            predicates.add(nome);
        }
        if(filtros.getCategoria().isPresent()) {
            Predicate categoria = cb.equal(produto.get("categoria"),filtros.getCategoria().get());
            predicates.add(categoria);
        }
        Predicate[] array = predicates.toArray(Predicate[]::new);
        cq.where(array);
        List<Produto> resultList = entityManager.createQuery(cq).getResultList();
        return resultList;
    }

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
