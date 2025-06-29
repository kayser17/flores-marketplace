package es.upm.dit.isst.florapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.upm.dit.isst.florapi.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.valoracion WHERE p.cliente.email = :email")
    List<Pedido> findByClienteEmail(@Param("email") String email);

    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.valoracion WHERE p.floricultor.email = :email")
    List<Pedido> findByFloricultorEmail(@Param("email") String email);

    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.valoracion WHERE p.idPedido = :id")
    Optional<Pedido> findByIdConValoracion(@Param("id") Long id);
}
