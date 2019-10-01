package com.mballem.curso.boot.domain;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@SuppressWarnings("serial")
@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {
	
	@NotBlank(message = "O nome do cargo é obrigatório")
	@Size(max = 60, message = "O nome do cargo deve conter no mmáximo 60 caracteres.")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;

	@NotNull(message = "Selecione o departamento relativo ao cargo.")
	@ManyToOne//relacionamento entre as entidades lê se da direita para esquera (departamento tem n cargos)
	@JoinColumn(name = "id_departamento_fk")//nome da chave estrageira que estara na tabela cargos
	private Departamento departamento;
	
	@OneToMany(mappedBy = "cargo")
	List<Funcionario> funcionarios;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
