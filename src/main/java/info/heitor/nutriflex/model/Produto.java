package info.heitor.nutriflex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUTO")
public class Produto {
    @NotBlank
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @Size(max = 300)
    private String descricao;
    private BigDecimal preco;
    private int estoque;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String codigo;

    @PrePersist
    public void prePersist() {
        // Gera o código automaticamente usando um UUID
        this.codigo = "PROD-" + UUID.randomUUID().toString();
    }

}
