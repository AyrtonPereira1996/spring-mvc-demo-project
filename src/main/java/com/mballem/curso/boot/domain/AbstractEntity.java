package com.mballem.curso.boot.domain;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@MappedSuperclass//para dizer ao jpa que enta classe e uma super classe das entidades que se iram implementar
public abstract class AbstractEntity<ID extends Serializable> implements Serializable { //sera util para as demais classes abstractas... classe abstracta responsavel por armazenar um id e os metodos get, set do id e tambem armazenar o metodo toString(), equals() e hashCode()
	 
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)//tipo identity e referente ao auto incremento do mysql
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id=" + id;
	}
	
	
	
	
	
}
