package com.example.demo.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Desarrollador;

@Repository
public interface DesarrolladorRepository extends JpaRepository<Desarrollador, Serializable> {

	@Bean
	public abstract List<Desarrollador> findAll();
	public abstract Desarrollador findById(int id);
}
