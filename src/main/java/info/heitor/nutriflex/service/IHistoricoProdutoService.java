package info.heitor.nutriflex.service;

import info.heitor.nutriflex.model.HistoricoProduto;
import info.heitor.nutriflex.model.Produto;

import java.util.List;

public interface IHistoricoProdutoService {

    public List<HistoricoProduto> buscarTodos();
}
