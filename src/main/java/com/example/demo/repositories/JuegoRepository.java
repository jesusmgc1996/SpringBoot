package com.example.demo.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Juego;

import jakarta.transaction.Transactional;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Serializable> {

	@Bean
	public abstract List<Juego> findAll();
	public abstract Juego findById(int id);
	
	@Transactional
	public abstract void deleteById(int id);
	public abstract Juego save(Juego u);
}
