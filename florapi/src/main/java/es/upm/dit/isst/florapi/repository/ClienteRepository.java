package es.upm.dit.isst.florapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.upm.dit.isst.florapi.model.*;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findByNombre(String nombre);
}
