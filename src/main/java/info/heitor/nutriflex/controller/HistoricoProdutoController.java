package info.heitor.nutriflex.controller;

import info.heitor.nutriflex.model.HistoricoProduto;
import info.heitor.nutriflex.service.HistoricoProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historicos")
@RequiredArgsConstructor
public class HistoricoProdutoController {

    private final HistoricoProdutoService historicoProdutoService;

    @GetMapping
    public List<HistoricoProduto> listar() {
        return historicoProdutoService.buscarTodos();
    }
}
