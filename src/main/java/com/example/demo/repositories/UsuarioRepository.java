package com.example.demo.repositories;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Serializable> {

	@Bean
	public abstract Usuario findByUsernameAndPass(String username, String pass);
	public abstract Usuario findById(int id);
}
