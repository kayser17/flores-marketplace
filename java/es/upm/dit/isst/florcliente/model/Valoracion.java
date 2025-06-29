package es.upm.dit.isst.florcliente.model;

import lombok.*;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Valoracion {

    private Long idValoracion;

    private int calificacionPedido;
    private int calificacionLogistica;

    private String comentario;
    private Date fecha;

    private Pedido pedido;
}
