package com.clientes.model.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateUserDto {
    @NotBlank(message = "{campo.login.obrigatorio}")
    private String username;

    @NotBlank(message = "{campo.senha.obrigatorio}")
    private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    
}