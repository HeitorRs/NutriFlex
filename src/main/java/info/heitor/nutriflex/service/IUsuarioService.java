package info.heitor.nutriflex.service;

import info.heitor.nutriflex.model.Role;
import info.heitor.nutriflex.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> buscarTodosUsuarios();

    List<Usuario> buscarTodosUsuariosAtivos();

    List<Usuario> buscarTodosUsuariosPorRoles(List<Role> roles);

    Optional<Usuario> buscarUsuarioPorId(Long id);

    Usuario salvarUsuario(Usuario usuario);

    Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado);

    void deletarUsuarioPorId(Long id);

}
