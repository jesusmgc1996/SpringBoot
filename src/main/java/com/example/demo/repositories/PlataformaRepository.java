package com.example.demo.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Plataforma;

@Repository
public interface PlataformaRepository extends JpaRepository<Plataforma, Serializable> {

	@Bean
	public abstract List<Plataforma> findAll();
	public abstract List<Plataforma> findByIdIn(List<Integer> ids);
}
