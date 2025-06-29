package es.upm.dit.isst.florcliente.controller;

import es.upm.dit.isst.florcliente.model.LineaPedido;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
@ControllerAdvice
public class GlobalAttributesController {

    @ModelAttribute("totalCarrito")
    public double getTotalCarrito(HttpSession session) {
        List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
        if (carrito == null) return 0.0;
        return carrito.stream()
                      .mapToDouble(lp -> lp.getCantidad() * lp.getPrecioUnitario())
                      .sum();
    }
}
