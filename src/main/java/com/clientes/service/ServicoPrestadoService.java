package com.clientes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clientes.model.entity.Servico;
import com.clientes.repository.ServicoRepository;

@Service
public class ServicoPrestadoService {

	@Autowired
	ServicoRepository servicoRepository;
	
}
