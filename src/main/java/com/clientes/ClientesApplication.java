package com.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientesApplication {

//	@Bean
//	public CommandLineRunner run(@Autowired ClienteRepository clienteRepository) {
//		return arg -> {
//			Cliente cliente = Cliente.builder().cpf("00000000000").nome("Fulano").build();
//			clienteRepository.save(cliente);
//		};
//	}
	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}
	
	
}
