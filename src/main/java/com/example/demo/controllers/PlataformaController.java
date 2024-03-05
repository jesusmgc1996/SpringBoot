package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Plataforma;
import com.example.demo.repositories.PlataformaRepository;

@CrossOrigin
@RestController
@RequestMapping("/plataforma")
public class PlataformaController {
	
	@Autowired
	PlataformaRepository plataformaRep;

	@GetMapping("")
	public List<DTO> listarTodos() {
		List<DTO> plataformasDTO = new ArrayList<DTO>();
		List<Plataforma> plataformas = plataformaRep.findAll();

		for (Plataforma p : plataformas) {
			DTO plataforma = new DTO();

			plataforma.put("id", p.getId());
			plataforma.put("nombre", p.getNombre());

			plataformasDTO.add(plataforma);
		}

		return plataformasDTO;
	}
}
