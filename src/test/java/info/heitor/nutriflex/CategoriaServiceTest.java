package info.heitor.nutriflex;

import info.heitor.nutriflex.model.Categoria;
import info.heitor.nutriflex.service.CategoriaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
        assertEquals(4,categorias.size());
    }

    @Test
    @DisplayName("Deve retornar por ID")
    public void testeBuscarCategoriaPorId() {
        Optional<Categoria> categoria = categoriaService.buscarCategoriaPorId(2L);
        assertTrue(categoria.isPresent());
    }
}
