package es.upm.dit.isst.florcliente.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.upm.dit.isst.florcliente.model.Cliente;
import es.upm.dit.isst.florcliente.model.Floricultor;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8080";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Cliente cliente = restTemplate.getForObject(baseUrl + "/clientes/" + email, Cliente.class);
            return User.withUsername(cliente.getEmail())
                       .password(cliente.getPassword())
                       .authorities("CLIENTE")
                       .build();
        } catch (Exception e1) {
            try {
                Floricultor floricultor = restTemplate.getForObject(baseUrl + "/floricultores/" + email, Floricultor.class);
                return User.withUsername(floricultor.getEmail())
                           .password(floricultor.getPassword())
                           .authorities("FLORICULTOR")
                           .build();
            } catch (Exception e2) {
                throw new UsernameNotFoundException("Usuario no encontrado: " + email);
            }
        }
    }
}
