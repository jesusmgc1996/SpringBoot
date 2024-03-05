package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

/**
 * The persistent class for the juegos database table.
 * 
 */
@Entity
@Table(name = "juegos")
@NamedQuery(name = "Juego.findAll", query = "SELECT j FROM Juego j")
public class Juego implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int anio;

	private String nombre;

	// bi-directional many-to-one association to Desarrollador
	@ManyToOne
	@JoinColumn(name = "desarrollador")
	private Desarrollador desarrollador;

	// bi-directional many-to-many association to Plataforma
	@ManyToMany
	@JoinTable(name = "juegos_plataformas", joinColumns = @JoinColumn(name = "id_juego"), inverseJoinColumns = @JoinColumn(name = "id_plataforma"))
	private List<Plataforma> plataformas;

	public Juego() {
	}

	public Juego(int id, String nombre, Desarrollador desarrollador, List<Plataforma> plataformas, int anio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.desarrollador = desarrollador;
		this.plataformas = plataformas;
		this.anio = anio;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnio() {
		return this.anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Desarrollador getDesarrollador() {
		return this.desarrollador;
	}

	public void setDesarrollador(Desarrollador desarrollador) {
		this.desarrollador = desarrollador;
	}

	public List<Plataforma> getPlataformas() {
		return this.plataformas;
	}

	public void setPlataformas(List<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}

}