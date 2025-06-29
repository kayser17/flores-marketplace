package es.upm.dit.isst.florapi.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Pedido {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @ToString.Include
    private Date fecha;

    @ToString.Include
    private String estado;
    private String destino;

    @ManyToOne
    @JoinColumn(name = "email_cliente") // Relación con Cliente
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "email_floricultor") // Relación con Floricultor
    private Floricultor floricultor;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("pedido")
    private Valoracion valoracion;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("pedido")
    private List<LineaPedido> lineasPedido;

    // Getters y Setters
    public double getTotal() {
        if (lineasPedido == null)
            return 0;

        return lineasPedido.stream()
                .mapToDouble(det -> det.getCantidad() * det.getPrecioUnitario())
                .sum();
    }
}
