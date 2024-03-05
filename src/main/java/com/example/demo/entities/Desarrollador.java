package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the desarrollador database table.
 * 
 */
@Entity
@NamedQuery(name="Desarrollador.findAll", query="SELECT d FROM Desarrollador d")
public class Desarrollador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Juego
	@OneToMany(mappedBy="desarrollador")
	private List<Juego> juegos;

	public Desarrollador() {
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

	public Juego addJuego(Juego juego) {
		getJuegos().add(juego);
		juego.setDesarrollador(this);

		return juego;
	}

	public Juego removeJuego(Juego juego) {
		getJuegos().remove(juego);
		juego.setDesarrollador(null);

		return juego;
	}

}