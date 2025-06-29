package es.upm.dit.isst.florapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.upm.dit.isst.florapi.model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
    @Query("SELECT COUNT(v) FROM Valoracion v JOIN v.pedido p JOIN p.lineasPedido l WHERE l.producto.idProducto = :productoId")
    long contarValoracionesPorProducto(@Param("productoId") Long productoId);

    @Query("SELECT COALESCE(AVG(v.calificacionPedido), 0) " +
            "FROM Valoracion v JOIN v.pedido p JOIN p.lineasPedido l WHERE l.producto.idProducto = :productoId")
    Double calcularMediaValoracionesPorProducto(@Param("productoId") Long productoId);

    @Query("SELECT COALESCE(AVG(v.calificacionPedido), 0) FROM Valoracion v JOIN v.pedido p WHERE p.floricultor.email = :email")
    Double calcularMediaValoracionesPorFloricultor(@Param("email") String email);

    @Query("SELECT COUNT(v) FROM Valoracion v JOIN v.pedido p WHERE p.floricultor.email = :email")
    Long contarValoracionesPorFloricultor(@Param("email") String email);

}
