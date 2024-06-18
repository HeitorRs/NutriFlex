package info.heitor.nutriflex.service;

import info.heitor.nutriflex.Filters.ProdutoFiltros;
import info.heitor.nutriflex.model.Produto;

import java.util.List;
import java.util.Optional;

public interface IProdutoService {

    public List<Produto> listarTodosProdutos();

    public Produto salvarProduto(Produto produto);

    public Optional<Produto> encontrarProdutoPorId(Long id);

    public void deletarProdutoPorId(Long id);

    public Produto atualizarProduto(Long id, Produto produtoAtualizado);

    List<Produto> buscarFiltros(ProdutoFiltros filtros) ;
}
