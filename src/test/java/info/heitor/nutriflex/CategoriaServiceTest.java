package info.heitor.nutriflex;

import info.heitor.nutriflex.model.Categoria;
import info.heitor.nutriflex.service.CategoriaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CategoriaServiceTest {

    @Autowired
    private CategoriaService categoriaService;

    @Test
    @DisplayName("Deve retornar todas as categorias")
    public void testeListarTodasCategorias() {
        List<Categoria> categorias = categoriaService.listarTodasCategorias();
        assertEquals(2,categorias.size());
    }

    @Test
    @DisplayName("Deve retornar por ID")
    public void testeBuscarCategoriaPorId() {
        Optional<Categoria> categoria = categoriaService.buscarCategoriaPorId(2L);
        assertTrue(categoria.isPresent());
    }

    @Test
    @DisplayName("Deve retornar uma categoria pelo nome")
    public void testebuscarCategoriaPorNome() {
        Categoria categoria = categoriaService.buscarCategoriaPorNomeContains("Sup");
        assertThat(categoria).isNotNull();
        assertThat(categoria.getNome()).contains("Suplemento");
    }
}
