package info.heitor.nutriflex.repository;

import info.heitor.nutriflex.model.Role;
import info.heitor.nutriflex.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.status = 1")
    List<Usuario> findAllAtivos();

    @Query(value = "SELECT u FROM Usuario u WHERE u.email=?1 OR u.nome=?2 ORDER BY u.nome asc limit 5")
    List<Usuario> findAllBy(String email,String nome);

    @Query(value = "SELECT u FROM Usuario u WHERE u.email=:emailUsuario OR u.nome=:nome ORDER BY u.email desc limit 10")
    List<Usuario> findAllByNomed(@Param("nome")String nome, @Param("emailUsuario") String email);

    @Query("SELECT u FROM Usuario u inner join u.roles roles where roles in :rolesList" )
    List<Usuario> findAllByRoles(@Param("rolesList")List<Role> roles);
}
