package info.heitor.nutriflex.service;

import info.heitor.nutriflex.model.HistoricoProduto;
import info.heitor.nutriflex.model.Produto;
import info.heitor.nutriflex.repository.HistoricoProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoricoProdutoService implements IHistoricoProdutoService{

    private final HistoricoProdutoRepository historicoProdutoRepository;


    @Override
    public List<HistoricoProduto> buscarTodos() {
        return historicoProdutoRepository.findAll();
    }
}
