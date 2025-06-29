package es.upm.dit.isst.florapi.controller;

import es.upm.dit.isst.florapi.model.Valoracion;
import es.upm.dit.isst.florapi.model.Pedido;
import es.upm.dit.isst.florapi.repository.ValoracionRepository;
import es.upm.dit.isst.florapi.repository.PedidoRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/valoraciones")
public class ValoracionController {

    private final ValoracionRepository valoracionRepository;
    private final PedidoRepository pedidoRepository;

    public ValoracionController(ValoracionRepository valoracionRepository, PedidoRepository pedidoRepository) {
        this.valoracionRepository = valoracionRepository;
        this.pedidoRepository = pedidoRepository;
    }

    // Obtener todas las valoraciones
    @GetMapping
    public List<Valoracion> obtenerTodas() {
        return valoracionRepository.findAll();
    }

    // Obtener una valoración por ID
    @GetMapping("/{id}")
    public ResponseEntity<Valoracion> obtenerPorId(@PathVariable Long id) {
        Optional<Valoracion> valoracion = valoracionRepository.findById(id);
        return valoracion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva valoración
    @PostMapping
    public ResponseEntity<Valoracion> crear(@RequestBody Valoracion valoracion) {
        if (valoracion.getPedido() == null || valoracion.getPedido().getIdPedido() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Pedido> optionalPedido = pedidoRepository.findById(valoracion.getPedido().getIdPedido());
        if (!optionalPedido.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Pedido pedido = optionalPedido.get();

        valoracion.setPedido(pedido);
        valoracion.setFecha(new Date());

        Valoracion nuevaValoracion = valoracionRepository.save(valoracion);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaValoracion);
    }

    @GetMapping("/producto/{id}/count")
    public ResponseEntity<Long> contarValoracionesProducto(@PathVariable Long id) {
        long total = valoracionRepository.contarValoracionesPorProducto(id);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/producto/{id}/media")
    public ResponseEntity<Double> mediaValoracionesProducto(@PathVariable Long id) {
        Double media = valoracionRepository.calcularMediaValoracionesPorProducto(id);
        if (media == null) {
            media = 0.0;
        }
        return ResponseEntity.ok(media);
    }

    @GetMapping("/floricultor/{email}/media")
    public ResponseEntity<Double> mediaValoracionesFloricultor(@PathVariable String email) {
        Double media = valoracionRepository.calcularMediaValoracionesPorFloricultor(email);
        return ResponseEntity.ok(media != null ? media : 0.0);
    }

    @GetMapping("/floricultor/{email}/count")
    public ResponseEntity<Long> contarValoracionesFloricultor(@PathVariable String email) {
        Long total = valoracionRepository.contarValoracionesPorFloricultor(email);
        return ResponseEntity.ok(total != null ? total : 0L);
    }

}
