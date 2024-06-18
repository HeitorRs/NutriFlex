package info.heitor.nutriflex.repository;

import info.heitor.nutriflex.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByNome(String nome);

    Categoria findByNomeContains(String nome);
}
