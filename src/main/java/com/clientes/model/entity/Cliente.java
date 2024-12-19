package com.clientes.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Entity
@Table(name = "tb_clientes")
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    @Length(max = 50, message = "{campo.nome.maximo}")
    private String nome;

    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column(nullable = false, length = 11)
    @Pattern(regexp = "\\d{10,11}", message = "{campo.tel.maximo}")
    private String tel1;

    @Column(length = 11)
    @Pattern(regexp = "\\d{10,11}", message = "{campo.tel.maximo}")
    private String tel2;

    @Column
    @Email(message = "{campo.email.valido}")
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("cliente") //Pra não repetir circular no response do JSON
    private List<Endereco> endereco;

    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
    
    @PrePersist
    public void prePersist() {
    	setDataCadastro(LocalDate.now());
    }
    

    public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getTel1() {
		return tel1;
	}



	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}



	public String getTel2() {
		return tel2;
	}



	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}



	public List<Endereco> getEndereco() {
		return endereco;
	}


	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDate getDataCadastro() {
		return dataCadastro;
	}



	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}



}
