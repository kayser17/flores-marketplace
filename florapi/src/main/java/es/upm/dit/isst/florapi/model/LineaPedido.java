package es.upm.dit.isst.florapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class LineaPedido {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idLinea;

    @ToString.Include
    private int cantidad;
    
    @ToString.Include
    private double precioUnitario; 
    
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("lineasPedido")
    private Pedido pedido;
    
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    //Getters y Setters

    public double getSubtotal() {
        return cantidad * precioUnitario;
    }
}
