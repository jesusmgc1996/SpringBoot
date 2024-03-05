package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the plataformas database table.
 * 
 */
@Entity
@Table(name="plataformas")
@NamedQuery(name="Plataforma.findAll", query="SELECT p FROM Plataforma p")
public class Plataforma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nombre;

	//bi-directional many-to-many association to Juego
	@ManyToMany(mappedBy="plataformas")
	private List<Juego> juegos;

	public Plataforma() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Juego> getJuegos() {
		return this.juegos;
	}

	public void setJuegos(List<Juego> juegos) {
		this.juegos = juegos;
	}

}