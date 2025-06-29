package es.upm.dit.isst.florcliente.controller;

import es.upm.dit.isst.florcliente.model.LineaPedido;
import es.upm.dit.isst.florcliente.model.Producto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
@RequestMapping("/carrito")
@SessionAttributes({"usuario", "rol"})
public class CarritoController {

    @ModelAttribute("usuario")
    public Object usuario() {
        return null;
    }

    @ModelAttribute("rol")
    public String rol() {
        return "";
    }

    //Agregar al carrito pero solo de un mismo floricultor: Importante esto.
    @PostMapping("/agregar")
    public String agregarProducto(@ModelAttribute Producto producto, @RequestParam int cantidad, HttpSession session, Model model) {
        List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
    
        String floricultorNuevo = producto.getFloricultor().getEmail();
        if (!carrito.isEmpty()) {
            String floricultorActual = carrito.get(0).getProducto().getFloricultor().getEmail();
            if (!floricultorActual.equals(floricultorNuevo)) {
                session.setAttribute("errorCarrito", "Este producto pertenece a otro floricultor. Solo puedes añadir productos del mismo floricultor en un pedido.");
                return "redirect:/producto/" + producto.getIdProducto();
            }
        }
    
        session.removeAttribute("errorCarrito");
    
        Optional<LineaPedido> existente = carrito.stream()
                .filter(lp -> lp.getProducto().getIdProducto().equals(producto.getIdProducto()))
                .findFirst();
    
        if (existente.isPresent()) {
            LineaPedido lp = existente.get();
            lp.setCantidad(lp.getCantidad() + cantidad);
        } else {
            LineaPedido nueva = new LineaPedido();
            nueva.setProducto(producto);
            nueva.setCantidad(cantidad);
            nueva.setPrecioUnitario(producto.getPrecio());
            carrito.add(nueva);
        }
    
        session.setAttribute("carrito", carrito);
        session.setAttribute("mensajeExito", "Producto añadido al carrito correctamente.");
        return "redirect:/producto/" + producto.getIdProducto();
    }
    
    

    @GetMapping("/ver")
    public String verCarrito(Model model, HttpSession session, @ModelAttribute("usuario") Object usuario) {
        List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
        if (carrito == null) carrito = new ArrayList<>();
        model.addAttribute("carrito", carrito);
        model.addAttribute("usuario", usuario);

        double total = carrito.stream().mapToDouble(LineaPedido::getSubtotal).sum();
        model.addAttribute("total", total);

        return "carrito";
    }

    @PostMapping("/vaciar")
    public String vaciarCarrito(HttpSession session) {
        session.removeAttribute("carrito");
        return "redirect:/home";
    }
    @PostMapping("/eliminar")
    public String eliminarDelCarrito(@RequestParam Long idProducto, HttpSession session) {
    List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
    if (carrito != null) {
        carrito.removeIf(lp -> lp.getProducto().getIdProducto().equals(idProducto));
    }
    return "redirect:/carrito/ver";
    }

    
    

    @ModelAttribute("totalCarrito")
    public double getTotalCarrito(HttpSession session) {
        List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
        if (carrito == null) return 0.0;
        return carrito.stream().mapToDouble(lp -> lp.getCantidad() * lp.getPrecioUnitario()).sum();
    }

    @PostMapping("/sumar")
    public String sumarCantidad(@RequestParam Long idProducto, HttpSession session) {
        List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
        if (carrito != null) {
            for (LineaPedido linea : carrito) {
                if (linea.getProducto().getIdProducto().equals(idProducto)) {
                    linea.setCantidad(linea.getCantidad() + 1);
                    break;
                }
            }
            session.setAttribute("carrito", carrito);
        }
        return "redirect:/carrito/ver";
    }

    @PostMapping("/restar")
    public String restarCantidad(@RequestParam Long idProducto, HttpSession session) {
        List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
        if (carrito != null) {
            Iterator<LineaPedido> iter = carrito.iterator();
            while (iter.hasNext()) {
                LineaPedido linea = iter.next();
                if (linea.getProducto().getIdProducto().equals(idProducto)) {
                    if (linea.getCantidad() > 1) {
                        linea.setCantidad(linea.getCantidad() - 1);
                    } else {
                        iter.remove();
                    }
                    break;
                }
            }
            session.setAttribute("carrito", carrito);
        }
        return "redirect:/carrito/ver";
    }
    @PostMapping("/agregar-ramos")
public String agregarRamoPersonalizado(
        @RequestParam("productoIds") List<Long> productoIds,
        @RequestParam("cantidades") List<Integer> cantidades,
        HttpSession session,
        Model model
) {
    // Asegúrate de usar la misma lista
    List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
    if (carrito == null) carrito = new ArrayList<>();

    String floricultorEmail = null;

    for (int i = 0; i < productoIds.size(); i++) {
        Producto producto = new RestTemplate().getForObject(
                "http://localhost:8080/productos/" + productoIds.get(i),
                Producto.class
        );

        if (producto == null) continue;

        if (floricultorEmail == null) {
            floricultorEmail = producto.getFloricultor().getEmail();
        } else if (!producto.getFloricultor().getEmail().equals(floricultorEmail)) {
            session.setAttribute("errorCarrito", "Todas las flores deben ser del mismo floricultor.");
            return "redirect:/flores";
        }

        int cantidad = cantidades.get(i);

        Optional<LineaPedido> existente = carrito.stream()
                .filter(lp -> lp.getProducto().getIdProducto().equals(producto.getIdProducto()))
                .findFirst();

        if (existente.isPresent()) {
            LineaPedido lp = existente.get();
            lp.setCantidad(lp.getCantidad() + cantidad);
        } else {
            LineaPedido lp = new LineaPedido();
            lp.setProducto(producto);
            lp.setCantidad(cantidad);
            lp.setPrecioUnitario(producto.getPrecio());
            carrito.add(lp);
        }
    }

    // Al final, guardar la lista completa modificada
    session.setAttribute("carrito", carrito);
    session.setAttribute("mensajeExito", "Ramo personalizado añadido correctamente.");
    return "redirect:/carrito/ver";
}


}
