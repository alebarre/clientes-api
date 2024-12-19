package com.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.clientes.model.entity.Endereco;
import com.clientes.repository.ClienteRepository;
import com.clientes.repository.EnderecoRepository;
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
	private final EnderecoRepository enderecoRepository;
	
	@Autowired
	public ClienteController(ClienteRepository clienteRepository, ServicoRepository servicoRepository, EnderecoRepository enderecoRepository) {
		this.clienteRepository = clienteRepository;
		this.servicoRepository = servicoRepository;
		this.enderecoRepository = enderecoRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		
	    if (cliente.getEndereco() != null && !cliente.getEndereco().isEmpty()) {
	    cliente.getEndereco().forEach(item -> item.setCliente(cliente));
	        return clienteRepository.save(cliente);
	    } else {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar o Endereço. 🙁");
	    }
	    
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
	public Cliente atualizar (@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {
		
		if (clienteAtualizado.getEndereco() != null && !clienteAtualizado.getEndereco().isEmpty()) {
			clienteAtualizado.getEndereco().forEach(item -> item.setCliente(clienteAtualizado));
		        return clienteRepository.save(clienteAtualizado);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Necessário cadastrar o Endereço.");
		}
		
	}
	
}
