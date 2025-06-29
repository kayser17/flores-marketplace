package es.upm.dit.isst.florcliente.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private String tipo; // "cliente" o "floricultor"
    private Cliente cliente;
    private Floricultor floricultor;

    // Constructor para Cliente
    public Usuario(Cliente cliente) {
        this.tipo = "cliente";
        this.cliente = cliente;
    }

    // Constructor para Floricultor
    public Usuario(Floricultor floricultor) {
        this.tipo = "floricultor";
        this.floricultor = floricultor;
    }

        // Método para obtener el nombre del usuario
        public String getNombre() {
            if (this.tipo.equals("cliente") && this.cliente != null) {
                return this.cliente.getNombre();
            } else if (this.tipo.equals("floricultor") && this.floricultor != null) {
                return this.floricultor.getNombre();
            }
            return null;
        }
}