package info.heitor.nutriflex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Integer status; //0-desativado | 1-ativo


    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;
}
