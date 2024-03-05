package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Usuario;
import com.example.demo.jwtSecurity.AutenticadorJWT;
import com.example.demo.repositories.UsuarioRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	static class DatosAutenticaUsuario {
		String username;
		String pass;

		public DatosAutenticaUsuario(String username, String pass) {
			super();
			this.username = username;
			this.pass = pass;
		}
	}

	@Autowired
	UsuarioRepository usuRep;

	@PostMapping(path = "/autentica", consumes = MediaType.APPLICATION_JSON_VALUE)
	public DTO autenticaUsuario(@RequestBody DatosAutenticaUsuario datos, HttpServletResponse response) {
		DTO result = new DTO();

		Usuario u = usuRep.findByUsernameAndPass(datos.username, datos.pass);

		if (u != null) {
			result.put("resultado", "ok");
			result.put("jwt", AutenticadorJWT.codificaJWT(u));
			Cookie cook = new Cookie("jwt", AutenticadorJWT.codificaJWT(u));
			cook.setMaxAge(-1);
			response.addCookie(cook);
		} else {
			result.put("resultado", "nulo");
		}

		return result;
	}

	@GetMapping("/quienEres")
	public DTO getAutenticado(HttpServletRequest request) {
		DTO usuario = new DTO();
		int id = -1;
		Cookie[] cookies = request.getCookies();

		for (Cookie c : cookies) {
			if (c.getName().equals("jwt")) {
				id = AutenticadorJWT.getIdUsuarioDesdeJWT(c.getValue());
			}
		}

		Usuario u = usuRep.findById(id);

		if (u != null) {
			usuario.put("usuario", u.getUsername());
			usuario.put("contraseña", u.getPass());
		} else {
			usuario.put("resultado", "nulo");
		}

		return usuario;
	}
}
