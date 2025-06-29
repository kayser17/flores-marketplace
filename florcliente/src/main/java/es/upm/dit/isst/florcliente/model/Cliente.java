package es.upm.dit.isst.florcliente.model;


import java.util.Date;
import java.util.List;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    
   
    private String nombre;
    private String email;
    private String password;

    private List<Pedido> pedidos;

    // Getters y Setters
}
