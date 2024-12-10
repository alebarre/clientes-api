package com.clientes.controller.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {
	
	private List<String> errors;
	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public ApiErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public ApiErrors(String message) {
		this.errors = Arrays.asList(message);
	}
	
}
