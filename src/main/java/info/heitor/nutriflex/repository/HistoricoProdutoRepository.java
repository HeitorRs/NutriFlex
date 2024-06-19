package info.heitor.nutriflex.repository;

import info.heitor.nutriflex.model.HistoricoProduto;
import info.heitor.nutriflex.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoProdutoRepository extends JpaRepository<HistoricoProduto, Long> {

    List<HistoricoProduto> findByCodigoProduto(String codigoProduto);
}
