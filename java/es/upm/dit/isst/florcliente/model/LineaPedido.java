package es.upm.dit.isst.florcliente.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LineaPedido {

    private Long idLinea;
    private int cantidad;
    private double precioUnitario;

    private Pedido pedido;
    private Producto producto;

    // Método para calcular subtotal
    public double getSubtotal() {
        return cantidad * precioUnitario;
    }
}
