package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Desarrollador;
import com.example.demo.entities.Juego;
import com.example.demo.entities.Plataforma;
import com.example.demo.repositories.DesarrolladorRepository;
import com.example.demo.repositories.JuegoRepository;
import com.example.demo.repositories.PlataformaRepository;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/juego")
public class JuegoController {

	static class DatosAltaJuego {
		int id;
		String nombre;
		int idDesarrollador;
		List<Integer> idsPlataformas;
		int anio;

		public DatosAltaJuego(int id, String nombre, int idDesarrollador, List<Integer> idsPlataformas, int anio) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.idDesarrollador = idDesarrollador;
			this.idsPlataformas = idsPlataformas;
			this.anio = anio;
		}
	}

	@Autowired
	JuegoRepository juegoRep;

	@Autowired
	DesarrolladorRepository desarrolladorRep;

	@Autowired
	PlataformaRepository plataformaRep;

	@GetMapping("")
	public List<DTO> listarTodos() {
		List<DTO> juegosDTO = new ArrayList<DTO>();
		List<Juego> juegos = juegoRep.findAll();

		for (Juego j : juegos) {
			DTO juego = new DTO();

			juego.put("id", j.getId());
			juego.put("nombre", j.getNombre());
			juego.put("idDesarrollador", j.getDesarrollador().getId());
			juego.put("desarrollador", j.getDesarrollador().getNombre());
			juego.put("idsPlataformas",
					j.getPlataformas().stream().map(Plataforma::getId).collect(Collectors.toList()));
			juego.put("plataformas",
					j.getPlataformas().stream().map(Plataforma::getNombre).collect(Collectors.toList()));
			juego.put("anio", j.getAnio());

			juegosDTO.add(juego);
		}

		return juegosDTO;
	}

	@PostMapping(path = "/insertar1", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertarVideojuego(@RequestBody DatosAltaJuego j, HttpServletRequest request) {
		Desarrollador desarrollador = desarrolladorRep.findById(j.idDesarrollador);
		List<Plataforma> plataformas = plataformaRep.findByIdIn(j.idsPlataformas);
		Juego juego = new Juego(j.id, j.nombre, desarrollador, plataformas, j.anio);
		juegoRep.save(juego);
	}

	@PostMapping(path = "/obtener1", consumes = MediaType.APPLICATION_JSON_VALUE)
	public DTO obtenerVideojuego(@RequestBody DTO id, HttpServletRequest request) {
		DTO juego = new DTO();
		Juego j = juegoRep.findById(Integer.parseInt(id.get("id").toString()));
		if (j != null) {
			juego.put("id", j.getId());
			juego.put("nombre", j.getNombre());
			juego.put("idDesarrollador", j.getDesarrollador().getId());
			juego.put("desarrollador", j.getDesarrollador().getNombre());
			juego.put("idsPlataformas",
					j.getPlataformas().stream().map(Plataforma::getId).collect(Collectors.toList()));
			juego.put("plataformas",
					j.getPlataformas().stream().map(Plataforma::getNombre).collect(Collectors.toList()));
			juego.put("anio", j.getAnio());
		} else {
			juego.put("resultado", "nulo");
		}

		return juego;
	}

	@PutMapping(path = "/actualizar1", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarLibro(@RequestBody DatosAltaJuego j, HttpServletRequest request) {
		Juego juego = juegoRep.findById(j.id);
		if (juego != null) {
			Desarrollador desarrollador = desarrolladorRep.findById(j.idDesarrollador);
			List<Plataforma> plataformas = plataformaRep.findByIdIn(j.idsPlataformas);
			juego.setNombre(j.nombre);
			juego.setDesarrollador(desarrollador);
			juego.getPlataformas().clear();
			juego.getPlataformas().addAll(plataformas);
			juego.setAnio(j.anio);
			juegoRep.save(juego);
		}
	}

	@DeleteMapping(path = "/borrar1", consumes = MediaType.APPLICATION_JSON_VALUE)
	public DTO borrarVideojuego(@RequestBody DTO id, HttpServletRequest request) {
		DTO resultado = new DTO();
		Juego j = juegoRep.findById(Integer.parseInt(id.get("id").toString()));
		if (j != null) {
			juegoRep.delete(j);
			resultado.put("borrado", "ok");
		} else {
			resultado.put("borrado", "fail");
		}

		return resultado;
	}
}
