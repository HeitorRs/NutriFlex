package info.heitor.nutriflex.service;

import info.heitor.nutriflex.model.Role;
import info.heitor.nutriflex.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> buscarTodosUsuarios();

    List<Usuario> buscarTodosUsuariosAtivos();

    List<Usuario> buscarTodosUsuariosPorRoles(List<Role> roles);

}
