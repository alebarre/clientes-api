package com.clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Entity
@Table(name = "tb_endereco")
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "{campo.logradouro.obrigatorio}")
    private String rua;

    @Column(nullable = false)
    @NotEmpty(message = "{campo.numero.obrigatorio}")
    private String numero;

    @Column(length = 50)
    private String complemento;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "{campo.bairro.obrigatorio}")
    private String bairro;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "{campo.cidade.obrigatorio}")
    private String cidade;

    @Column(nullable = false, length = 2)
    @NotEmpty(message = "{campo.uf.obrigatorio}")
    private String estado;

    @Column(nullable = false, length = 8)
    @NotEmpty(message = "{campo.cep.obrigatorio}")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("endereco") 
    private Cliente cliente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
    
    
    
}
