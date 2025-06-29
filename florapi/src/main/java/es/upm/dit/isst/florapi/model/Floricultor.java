package es.upm.dit.isst.florapi.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"pedidos", "productos"})
public class Floricultor {

    @EqualsAndHashCode.Include
    @Id 
    private String email;

    private String nombre;
    private String ubicacion;
    private double latitud;
    private double longitud;
    private boolean disponibilidad;
    private String password;

    @OneToMany(mappedBy = "floricultor")
    @JsonIgnore
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "floricultor")
    @JsonIgnore 
    private List<Producto> productos;
}
