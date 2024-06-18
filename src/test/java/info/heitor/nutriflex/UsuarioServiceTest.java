package info.heitor.nutriflex;

import info.heitor.nutriflex.model.Usuario;
import info.heitor.nutriflex.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;


    @Test
    @DisplayName("Deve buscar todos os usuários")
    public void testBuscarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();

        assertTrue(!usuarios.isEmpty());
    }

    @Test
    @DisplayName("Deve buscar todos os usuários ativos")
    public void testBuscarTodosUsuariosAtivos() {
        List<Usuario> usuariosAtivos = usuarioService.buscarTodosUsuariosAtivos();

        assertTrue(!usuariosAtivos.isEmpty());
    }
}
