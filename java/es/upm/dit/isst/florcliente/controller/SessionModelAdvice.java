package es.upm.dit.isst.florcliente.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@ControllerAdvice
public class SessionModelAdvice {

    @ModelAttribute
    public void addLatLngToModel(HttpSession session, Model model) {
        model.addAttribute("lat", session.getAttribute("lat"));
        model.addAttribute("lng", session.getAttribute("lng"));
    }
}
