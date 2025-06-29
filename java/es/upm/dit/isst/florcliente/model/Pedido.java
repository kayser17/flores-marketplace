package es.upm.dit.isst.florcliente.model;

import lombok.*;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Pedido {
    private Long idPedido;
    private Date fecha;
    private String estado;
    private String destino;

    private Cliente cliente;
    private Floricultor floricultor;
    private Valoracion valoracion;

    private List<LineaPedido> lineasPedido;

    public double getTotal() {
        if (lineasPedido == null) return 0;
        return lineasPedido.stream()
                .mapToDouble(det -> det.getCantidad() * det.getPrecioUnitario())
                .sum();
    }
}
