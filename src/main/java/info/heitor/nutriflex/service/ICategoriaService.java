package info.heitor.nutriflex.service;

import info.heitor.nutriflex.model.Categoria;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ICategoriaService {

    List<Categoria> listarTodasCategorias();

    Optional<Categoria> buscarCategoriaPorId(Long id);

    Categoria buscarCategoriaPorNome(String nome);

    Categoria buscarCategoriaPorNomeContains(String text);

    void salvarCategoria(Categoria categoria);

    void deletarCategoriaPorId(Long id);

    Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada);
}
