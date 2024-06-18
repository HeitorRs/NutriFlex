package info.heitor.nutriflex.service;

import info.heitor.nutriflex.model.Role;
import info.heitor.nutriflex.model.Usuario;
import info.heitor.nutriflex.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> buscarTodosUsuariosAtivos() {
        return usuarioRepository.findAllAtivos();
    }

    @Override
    public List<Usuario> buscarTodosUsuariosPorRoles(List<Role> roles) {
        return usuarioRepository.findAllByRoles(roles);
    }
}
