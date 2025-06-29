package es.upm.dit.isst.florcliente.controller;

import es.upm.dit.isst.florcliente.model.Producto;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class CategoriaController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String baseUrl = "http://localhost:8080";

   @GetMapping("/{tipoFlor}")
public String filtrarPorFlor(
    @PathVariable String tipoFlor,
    @RequestParam(required = false) String color,
    @RequestParam(required = false) Double precioMin,
    @RequestParam(required = false) Double precioMax,
    @RequestParam(required = false) Boolean disponible,
    @RequestParam(required = false) String ocasion, 
    Model model,
    HttpSession session
) {
    try {
        ResponseEntity<List<Producto>> response = restTemplate.exchange(
            baseUrl + "/productos",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Producto>>() {}
        );

        List<Producto> productos = response.getBody();
        Double lat = (Double) session.getAttribute("lat");
        Double lng = (Double) session.getAttribute("lng");

        if (productos != null) {
            
            productos = productos.stream()
                .filter(p -> p.getTipoFlor().equalsIgnoreCase(tipoFlor))
                .toList();

            if (lat != null && lng != null) {
                final double MAX_DIST = 50.0;
                Set<String> floricultoresCercanos = productos.stream()
                    .map(Producto::getFloricultor)
                    .distinct()
                    .filter(f -> calcularDistancia(lat, lng, f.getLatitud(), f.getLongitud()) <= MAX_DIST)
                    .map(f -> f.getEmail())
                    .collect(Collectors.toSet());

                productos = productos.stream()
                    .filter(p -> floricultoresCercanos.contains(p.getFloricultor().getEmail()))
                    .toList();
            }

            productos = productos.stream()
                .filter(p -> color == null || color.isEmpty() || p.getColor().equalsIgnoreCase(color))
                .filter(p -> (precioMin == null || p.getPrecio() >= precioMin) &&
                             (precioMax == null || p.getPrecio() <= precioMax))
                .filter(p -> disponible == null || !disponible || p.getCantidad() > 0)
                .filter(p -> ocasion == null || ocasion.isEmpty() || 
                             (p.getOcasion() != null && p.getOcasion().equalsIgnoreCase(ocasion)))
                .toList();
        }

        model.addAttribute("productos", productos);
        model.addAttribute("tipoFlor", tipoFlor);
        model.addAttribute("tituloCategoria", obtenerNombrePlural(tipoFlor));
        model.addAttribute("color", color);
        model.addAttribute("precioMin", precioMin);
        model.addAttribute("precioMax", precioMax);
        model.addAttribute("disponible", disponible);
        model.addAttribute("ocasion", ocasion);

    } catch (Exception e) {
        model.addAttribute("productos", new ArrayList<>());
    }

    return "productosPorCategoria";
}
private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
    final int R = 6371; // Radio de la Tierra en km
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);
    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(dLon / 2) * Math.sin(dLon / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return R * c;
}

    private String obtenerNombrePlural(String tipoFlor) {
        return switch (tipoFlor.toLowerCase()) {
            case "rosa" -> "Rosas";
            case "tulipán", "tulipan" -> "Tulipanes";
            case "girasol" -> "Girasoles";
            case "lirio" -> "Lirios";
            case "margarita" -> "Margaritas";
            case "peonía", "peonia" -> "Peonías";
            default -> tipoFlor.substring(0, 1).toUpperCase() + tipoFlor.substring(1) + "s";
        };
    }

    @GetMapping("/ramos")
    public String filtrarRamosConFiltros(
        @RequestParam(required = false) String color,
        @RequestParam(required = false) Double precioMin,
        @RequestParam(required = false) Double precioMax,
        @RequestParam(required = false) Boolean disponible,
        @RequestParam(required = false) String ocasion,
        Model model,
        HttpSession session
    ) {
        try {
            ResponseEntity<List<Producto>> response = restTemplate.exchange(
                baseUrl + "/productos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Producto>>() {}
            );
    
            List<Producto> productos = response.getBody();
            Double lat = (Double) session.getAttribute("lat");
            Double lng = (Double) session.getAttribute("lng");
    
            if (productos != null) {
                productos = productos.stream()
                    .filter(Producto::isEsRamo)
                    .toList();
    
                if (lat != null && lng != null) {
                    final double MAX_DIST = 50.0;
                    Set<String> floricultoresCercanos = productos.stream()
                        .map(Producto::getFloricultor)
                        .distinct()
                        .filter(f -> calcularDistancia(lat, lng, f.getLatitud(), f.getLongitud()) <= MAX_DIST)
                        .map(f -> f.getEmail())
                        .collect(Collectors.toSet());
    
                    productos = productos.stream()
                        .filter(p -> floricultoresCercanos.contains(p.getFloricultor().getEmail()))
                        .toList();
                }
    
                productos = productos.stream()
                    .filter(p -> color == null || color.isEmpty() || p.getColor().equalsIgnoreCase(color))
                    .filter(p -> (precioMin == null || p.getPrecio() >= precioMin) &&
                                 (precioMax == null || p.getPrecio() <= precioMax))
                    .filter(p -> disponible == null || !disponible || p.getCantidad() > 0)
                    .filter(p -> ocasion == null || ocasion.isEmpty() || 
                                 (p.getOcasion() != null && p.getOcasion().equalsIgnoreCase(ocasion)))
                    .toList();
            }
    
            model.addAttribute("productos", productos);
            model.addAttribute("tituloCategoria", "Ramos");
            model.addAttribute("tipoFlor", "ramos");
            model.addAttribute("color", color);
            model.addAttribute("precioMin", precioMin);
            model.addAttribute("precioMax", precioMax);
            model.addAttribute("disponible", disponible);
            model.addAttribute("ocasion", ocasion);
    
        } catch (Exception e) {
            model.addAttribute("productos", new ArrayList<>());
            model.addAttribute("tituloCategoria", "Ramos");
        }
    
        return "productosPorCategoria";
    }
    
    

}
