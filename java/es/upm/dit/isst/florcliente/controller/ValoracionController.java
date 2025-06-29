package es.upm.dit.isst.florcliente.controller;

import es.upm.dit.isst.florcliente.model.Pedido;
import es.upm.dit.isst.florcliente.model.Valoracion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes({ "usuario", "rol" })
public class ValoracionController {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8080";

    public ValoracionController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/valoraciones/nueva")
    public String mostrarFormularioValoracion(@RequestParam Long pedidoId,
                                              Model model,
                                              @ModelAttribute("usuario") Object usuario,
                                              @ModelAttribute("rol") String rol) {
        model.addAttribute("pedidoId", pedidoId);
        model.addAttribute("usuario", usuario);
        model.addAttribute("rol", rol);
        return "valoracion";
    }
    

    @PostMapping("/valoraciones/guardar")
    public String guardarValoracion(@RequestParam Long pedidoId,
                                     @RequestParam int puntuacion,
                                     @RequestParam String comentario) {

        Valoracion valoracion = new Valoracion();
        valoracion.setCalificacionPedido(puntuacion);
        valoracion.setCalificacionLogistica(puntuacion);
        valoracion.setComentario(comentario);

        Pedido pedido = restTemplate.getForObject(baseUrl + "/pedidos/" + pedidoId, Pedido.class);
        valoracion.setPedido(pedido);

        restTemplate.postForObject(baseUrl + "/valoraciones", valoracion, Valoracion.class);

        return "redirect:/cuenta";
    }
}
