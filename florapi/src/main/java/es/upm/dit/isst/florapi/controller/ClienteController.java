package es.upm.dit.isst.florapi.controller;

import es.upm.dit.isst.florapi.model.Cliente;
import es.upm.dit.isst.florapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Obtener todos los clientes
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    // Crear un cliente
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return clienteRepository.save(cliente);
    }

    // Obtener un cliente por email (String)
    @GetMapping("/{email}")
    public ResponseEntity<Cliente> getClienteByEmail(@PathVariable String email) {
        return clienteRepository.findById(email)
            .map(cliente -> ResponseEntity.ok().body(cliente))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar un cliente (por email)
    @PutMapping("/{email}")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente newCliente, @PathVariable String email) {
        return clienteRepository.findById(email).map(cliente -> {
            cliente.setEmail(newCliente.getEmail());
            cliente.setNombre(newCliente.getNombre());
            clienteRepository.save(cliente);
            return ResponseEntity.ok().body(cliente);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar parcialmente un cliente (por email)
    @PatchMapping("/{email}")
    public ResponseEntity<Cliente> partialUpdateCliente(@RequestBody Cliente newCliente, @PathVariable String email) {
        return clienteRepository.findById(email).map(cliente -> {
            if (newCliente.getNombre() != null) {
                cliente.setNombre(newCliente.getNombre());
            }
            clienteRepository.save(cliente);
            return ResponseEntity.ok().body(cliente);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un cliente (por email)
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCliente(@PathVariable String email) {
        if (!clienteRepository.existsById(email)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(email);
        return ResponseEntity.noContent().build();
    }
}


