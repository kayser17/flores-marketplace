package es.upm.dit.isst.florapi.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import es.upm.dit.isst.florapi.model.*;



public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Long> {
    List<LineaPedido> findByPedido_IdPedido(Long idPedido);
}
