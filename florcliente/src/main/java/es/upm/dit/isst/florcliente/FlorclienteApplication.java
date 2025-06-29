package es.upm.dit.isst.florcliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@SpringBootApplication
public class FlorclienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlorclienteApplication.class, args);
	}



}
