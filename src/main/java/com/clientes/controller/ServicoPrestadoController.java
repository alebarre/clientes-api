package com.clientes.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.clientes.model.dto.ServicoPrestadoDTO;
import com.clientes.model.entity.Cliente;
import com.clientes.model.entity.Servico;
import com.clientes.repository.ClienteRepository;
import com.clientes.repository.ServicoRepository;
import com.clientes.util.BigDecimalConverter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {
	
	private final ServicoRepository servicoRepository;
	private final ClienteRepository clienteRepository;
	private final BigDecimalConverter bigDecimalConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Servico salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();
		
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));
		
		Servico servico = new Servico();
		
		servico.setDescricao(dto.getDescricao());
		servico.setData(data);
		servico.setCliente(cliente);
		servico.setValor(bigDecimalConverter.converter(dto.getPreco()));
		
		return servicoRepository.save(servico);
		
	}
	
	@GetMapping
	public List<Servico> pesquisar (
			@RequestParam(value="nome", required = false, defaultValue = "") String nome,
			@RequestParam(value="mes", required = false) Integer mes
	){
		return servicoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
		
	}

}
