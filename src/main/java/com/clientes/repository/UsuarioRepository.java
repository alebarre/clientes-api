package com.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientes.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
