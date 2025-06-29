package es.upm.dit.isst.florapi.controller;

import es.upm.dit.isst.florapi.model.Pedido;
import es.upm.dit.isst.florapi.repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import es.upm.dit.isst.florapi.model.LineaPedido;

import es.upm.dit.isst.florapi.model.Producto;
import es.upm.dit.isst.florapi.repository.ProductoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoController(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    // Get all pedidos
    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    // Get a pedido by ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findByIdConValoracion(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPedido(@RequestBody Pedido pedido) {
        if (pedido.getCliente() == null) {
            return ResponseEntity.badRequest().body("El campo 'cliente' es obligatorio.");
        }
        if (pedido.getEstado() == null) {
            return ResponseEntity.badRequest().body("El campo 'estado' es obligatorio.");
        }
        if (pedido.getLineasPedido() == null || pedido.getLineasPedido().isEmpty()) {
            return ResponseEntity.badRequest().body("El pedido debe contener al menos una línea.");
        }

        // Asociar cada LineaPedido con el Pedido actual
        for (LineaPedido lp : pedido.getLineasPedido()) {
            Producto producto = lp.getProducto();
            if (producto == null || producto.getIdProducto() == null) {
                return ResponseEntity.badRequest().body("Cada línea debe contener un producto válido.");
            }

            Producto productoBD = productoRepository.findById(producto.getIdProducto()).orElse(null);
            if (productoBD == null) {
                return ResponseEntity.badRequest().body("Producto no encontrado: ID " + producto.getIdProducto());
            }

            if (productoBD.getCantidad() < lp.getCantidad()) {
                return ResponseEntity.badRequest()
                        .body("Stock insuficiente para el producto: " + productoBD.getNombre());
            }

            // Reducimos el stock
            productoBD.setCantidad(productoBD.getCantidad() - lp.getCantidad());
            productoRepository.save(productoBD);

            // Aseguramos que la línea esté enlazada al producto y pedido correcto
            lp.setProducto(productoBD);
            lp.setPedido(pedido);
        }
        Pedido nuevo = pedidoRepository.save(pedido);
        return ResponseEntity.ok(nuevo);
    }

    // Get pedidos by cliente email
    // This method retrieves all pedidos associated with a specific cliente email.
    @GetMapping("/cliente/{email}")
    public List<Pedido> getPedidosByCliente(@PathVariable String email) {
        return pedidoRepository.findByClienteEmail(email);
    }

    // Get pedidos by floricultor email
    @GetMapping("/floricultor/{email}")
    public List<Pedido> getPedidosPorFloricultor(@PathVariable String email) {
        return pedidoRepository.findByFloricultorEmail(email); // o como se llame tu método
    }

    // Floricultor marca el pedido como ENVIADO
    @PostMapping("/{id}/enviar")
    public ResponseEntity<?> marcarComoEnviado(@PathVariable Long id) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (!optionalPedido.isPresent()) {
            return ResponseEntity.badRequest().body("Pedido no encontrado con ID " + id);
        }

        Pedido pedido = optionalPedido.get();
        pedido.setEstado("ENVIADO");
        pedidoRepository.save(pedido);
        return ResponseEntity.ok("Pedido marcado como ENVIADO");
    }

    // Cliente marca el pedido como COMPLETADO (recibido)
    @PostMapping("/{id}/completar")
    public ResponseEntity<?> marcarComoCompletado(@PathVariable Long id) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (!optionalPedido.isPresent()) {
            return ResponseEntity.badRequest().body("Pedido no encontrado con ID " + id);
        }

        Pedido pedido = optionalPedido.get();
        pedido.setEstado("COMPLETADO");
        pedidoRepository.save(pedido);
        return ResponseEntity.ok("Pedido marcado como COMPLETADO");
    }

    // Update an existing pedido
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePedido(@PathVariable Long id, @RequestBody Pedido pedidoDetails) {
        if (pedidoDetails.getCliente() == null) {
            return ResponseEntity.badRequest().body("El campo 'cliente' es obligatorio.");
        }
        if (pedidoDetails.getEstado() == null) {
            return ResponseEntity.badRequest().body("El campo 'estado' es obligatorio.");
        }

        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (!optionalPedido.isPresent()) {
            return ResponseEntity.badRequest().body("No se encontró el pedido con ID " + id);
        }

        Pedido pedido = optionalPedido.get();
        pedido.setFecha(pedidoDetails.getFecha());
        pedido.setEstado(pedidoDetails.getEstado());
        pedido.setDestino(pedidoDetails.getDestino());
        pedido.setCliente(pedidoDetails.getCliente());
        pedido.setFloricultor(pedidoDetails.getFloricultor());
        pedido.setValoracion(pedidoDetails.getValoracion());

        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }

    // Delete a pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable Long id) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedidoRepository.delete(pedido);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
