package es.upm.dit.isst.florcliente.controller;

import es.upm.dit.isst.florcliente.model.Cliente;
import es.upm.dit.isst.florcliente.model.Floricultor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class RegistroController {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8080"; // URL del backend
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistroController() {
        this.restTemplate = new RestTemplate();
        this.passwordEncoder = new BCryptPasswordEncoder(); // cifrador de contraseñas
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("tipoUsuario", "cliente");
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("floricultor", new Floricultor());
        return "registro";
    }

    @PostMapping("/registro")
public String registrarUsuario(
        @RequestParam String tipoUsuario,
        @RequestParam(required = false) String ciudad,
        @RequestParam(required = false) String direccion,
        @RequestParam(required = false) Double lat,
        @RequestParam(required = false) Double lng,
        @ModelAttribute Cliente cliente,
        @ModelAttribute Floricultor floricultor,
        Model model) {

    boolean emailExiste = false;

    if ("cliente".equals(tipoUsuario)) {
        try {
            restTemplate.getForObject(baseUrl + "/clientes/" + cliente.getEmail(), Cliente.class);
            emailExiste = true;
        } catch (Exception ignored) {}

        if (emailExiste) {
            model.addAttribute("error", "Ya existe un cliente registrado con ese email.");
            model.addAttribute("tipoUsuario", "cliente");
            model.addAttribute("cliente", cliente);
            model.addAttribute("floricultor", new Floricultor());
            return "registro";
        }

        restTemplate.postForObject(baseUrl + "/clientes", cliente, Cliente.class);

    } else if ("floricultor".equals(tipoUsuario)) {
        try {
            restTemplate.getForObject(baseUrl + "/floricultores/" + floricultor.getEmail(), Floricultor.class);
            emailExiste = true;
        } catch (Exception ignored) {}

        if (emailExiste) {
            model.addAttribute("error", "Ya existe un floricultor registrado con ese email.");
            model.addAttribute("tipoUsuario", "floricultor");
            model.addAttribute("cliente", new Cliente());
            model.addAttribute("floricultor", floricultor);
            return "registro";
        }

        floricultor.setUbicacion(ciudad);
        floricultor.setLatitud(lat);
        floricultor.setLongitud(lng);
        floricultor.setDisponibilidad(true);

        restTemplate.postForObject(baseUrl + "/floricultores", floricultor, Floricultor.class);
    }

    return "redirect:/login";
}


}

