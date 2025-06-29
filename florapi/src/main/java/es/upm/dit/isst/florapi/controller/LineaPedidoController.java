package es.upm.dit.isst.florapi.controller;

import es.upm.dit.isst.florapi.model.LineaPedido;
import es.upm.dit.isst.florapi.repository.LineaPedidoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/LineasPedido")
public class LineaPedidoController {

    private final LineaPedidoRepository lineaPedidoRepository;

    public LineaPedidoController(LineaPedidoRepository lineaPedidoRepository) {
        this.lineaPedidoRepository = lineaPedidoRepository;
    }

    // Obtener todos los Lineas de pedido
    @GetMapping
    public List<LineaPedido> obtenerTodos() {
        return lineaPedidoRepository.findAll();
    }

    // Obtener un Linea de pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<LineaPedido> obtenerPorId(@PathVariable Long id) {
        Optional<LineaPedido> lineaPedido = lineaPedidoRepository.findById(id);
        return lineaPedido.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo Linea de pedido
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody LineaPedido lineaPedido) {
        if (lineaPedido.getProducto() == null || lineaPedido.getPedido() == null || lineaPedido.getCantidad() <= 0) {
            return ResponseEntity.badRequest().body("Debe incluir producto, pedido y cantidad > 0");
        }
        LineaPedido nueva = lineaPedidoRepository.save(lineaPedido);
        return ResponseEntity.ok(nueva);
    }

    // Actualizar un Linea de pedido
    @PutMapping("/{id}")
    public ResponseEntity<LineaPedido> actualizar(@PathVariable Long id, @RequestBody LineaPedido lineaPedido) {
        if (!lineaPedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        lineaPedido.setIdLinea(id); // Asegurar que el ID coincide
        LineaPedido actualizado = lineaPedidoRepository.save(lineaPedido);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un Linea de pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!lineaPedidoRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("No se encontró la línea con ID " + id);
        }
        lineaPedidoRepository.deleteById(id);
        return ResponseEntity.badRequest().body("No se encontró la línea con ID " + id);
    }

    //Ver lineas de pedido según el id
    @GetMapping("/pedido/{idPedido}")
    public List<LineaPedido> obtenerPorPedido(@PathVariable Long idPedido) {
        return lineaPedidoRepository.findByPedido_IdPedido(idPedido);
    }
    @GetMapping("/vista/pedido/{idPedido}")
    public String mostrarLineasPedido(@PathVariable Long idPedido, Model model) {
        List<LineaPedido> lineas = lineaPedidoRepository.findByPedido_IdPedido(idPedido);
        model.addAttribute("lineas", lineas);
        model.addAttribute("idPedido", idPedido);
        return "lineasPedidoVista";
    }
    
}

