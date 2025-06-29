package es.upm.dit.isst.florapi.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "pedidos")
public class Cliente {
    
    @EqualsAndHashCode.Include
    @Id 
    private String email;  // email como identificador único
    private String nombre;
    private String password;


    @OneToMany(mappedBy = "cliente")
    @JsonIgnore 
    private List<Pedido> pedidos;
    
    // Getters y Setters
}
