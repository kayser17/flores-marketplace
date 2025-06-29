package es.upm.dit.isst.florapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.upm.dit.isst.florapi.model.*;
import java.util.List;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByFloricultorEmail(String email);
    List<Producto> findByTipoFlorIgnoreCase(String tipoFlor);
    List<Producto> findByEsRamoTrue();
}
