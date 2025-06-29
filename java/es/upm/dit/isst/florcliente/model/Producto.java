package es.upm.dit.isst.florcliente.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Producto {

    private Long idProducto;
    private String nombre;
    private String tipoFlor;
    private String color;
    private double precio;
    private int cantidad;
    private String imagen;
    private boolean esRamo;
    private String ocasion;


    private Floricultor floricultor;
}
