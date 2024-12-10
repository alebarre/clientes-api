package com.clientes.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.clientes.controller.exception.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationErrors(MethodArgumentNotValidException ex) {
		BindingResult bindingResult =  ex.getBindingResult();
		List<String> messages = bindingResult.getAllErrors()
				.stream()
				.map(objectError -> objectError
						.getDefaultMessage())
				.collect(Collectors.toList());
		
		return new ApiErrors(messages);
		
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusException (ResponseStatusException ex){
		String menssagemErro = ex.getReason();
		HttpStatus codigoStatus = (HttpStatus) ex.getStatusCode();
		ApiErrors apiErrors = new ApiErrors(menssagemErro);
		return new ResponseEntity(apiErrors, codigoStatus);
	}
}
