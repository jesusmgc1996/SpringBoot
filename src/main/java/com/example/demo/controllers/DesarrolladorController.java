package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Desarrollador;
import com.example.demo.repositories.DesarrolladorRepository;

@CrossOrigin
@RestController
@RequestMapping("/desarrollador")
public class DesarrolladorController {
	
	@Autowired
	DesarrolladorRepository desarrolladorRep;

	@GetMapping("")
	public List<DTO> listarTodos() {
		List<DTO> desarrolladoresDTO = new ArrayList<DTO>();
		List<Desarrollador> desarrolladores = desarrolladorRep.findAll();

		for (Desarrollador d : desarrolladores) {
			DTO desarrollador = new DTO();

			desarrollador.put("id", d.getId());
			desarrollador.put("nombre", d.getNombre());

			desarrolladoresDTO.add(desarrollador);
		}

		return desarrolladoresDTO;
	}
}
