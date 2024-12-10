package com.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.clientes.model.entity.Cliente;
import com.clientes.repository.ClienteRepository;
import com.clientes.repository.ServicoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private final ClienteRepository clienteRepository;
	
	@Autowired
	private final ServicoRepository servicoRepository;
	
	@Autowired
	public ClienteController(ClienteRepository clienteRepository, ServicoRepository servicoRepository) {
		this.clienteRepository = clienteRepository;
		this.servicoRepository = servicoRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> listarTodos () {
		return clienteRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Cliente acharId(@PathVariable Integer id) {
		return clienteRepository
				.findById(id)
				.orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado 🙁"));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar (@PathVariable Integer id) {
		clienteRepository
				.findById(id)
				.map(cliente -> {
	                servicoRepository.deleteAllByClienteId(cliente.getId());
					clienteRepository.delete(cliente);
					return Void.TYPE;
				})
				.orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado 🙁"));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar (@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {
		clienteRepository
		.findById(id)
		.map(cliente -> {
			cliente.setNome(clienteAtualizado.getNome());
			cliente.setCpf(clienteAtualizado.getCpf());
			return clienteRepository.save(clienteAtualizado);
		})
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
}
