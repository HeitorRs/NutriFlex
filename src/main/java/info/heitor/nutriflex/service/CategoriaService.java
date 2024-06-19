package info.heitor.nutriflex.service;

import info.heitor.nutriflex.model.Categoria;
import info.heitor.nutriflex.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarTodasCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria buscarCategoriaPorNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }

    @Override
    public Categoria buscarCategoriaPorNomeContains(String text) {
        return categoriaRepository.findByNomeContains(text);
    }

    @Override
    public void salvarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }
}
