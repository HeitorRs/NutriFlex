package info.heitor.nutriflex.service;

import info.heitor.nutriflex.model.Role;
import info.heitor.nutriflex.model.Usuario;
import info.heitor.nutriflex.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Override
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }
        validarUsuario(usuarioAtualizado);
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário de ID: " + id + " não encontrado"));
        usuarioAtualizado.setId(id);
        return usuarioRepository.save(usuarioAtualizado);
    }

    @Override
    public void deletarUsuarioPorId(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário não pode estar em branco");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email do usuário não pode estar em branco");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha do usuário não pode estar em branco");
        }
        if (usuario.getStatus() == null || (usuario.getStatus() != 0 && usuario.getStatus() != 1)) {
            throw new IllegalArgumentException("Status do usuário deve ser 0 (desativado) ou 1 (ativo)");
        }
    }
}
