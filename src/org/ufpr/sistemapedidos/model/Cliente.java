package org.ufpr.sistemapedidos.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Caio Calo
 */
public class Cliente {

	private final IntegerProperty id;
	private final StringProperty cpf;
	private final StringProperty nome;
	private final StringProperty sobreNome;

	public Cliente() {
		this(0, null, null, null);
	}

	public Cliente(Integer id, String cpf, String nome, String sobreNome) {
		this.id = new SimpleIntegerProperty(id);
		this.cpf = new SimpleStringProperty(cpf);
		this.nome = new SimpleStringProperty(nome);
		this.sobreNome = new SimpleStringProperty(sobreNome);
	}

	// GETTERS AND SETTERS
	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id.set(id);
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public String getCpf() {
		return cpf.get();
	}

	public void setCpf(String cpf) {
		this.cpf.set(cpf);
	}

	public StringProperty cpfProperty() {
		return cpf;
	}

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public StringProperty nomeProperty() {
		return nome;
	}

	public String getSobreNome() {
		return sobreNome.get();
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome.set(sobreNome);
	}

	public StringProperty sobreNomeProperty() {
		return sobreNome;
	}
	
	public StringProperty nomeCompletoProperty() {
		return new SimpleStringProperty(getNome() + " " + getSobreNome());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sobreNome == null) ? 0 : sobreNome.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.get().equals(other.cpf.get()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!(id.get() == other.id.get()))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.get().equals(other.nome.get()))
			return false;
		if (sobreNome == null) {
			if (other.sobreNome != null)
				return false;
		} else if (!sobreNome.get().equals(other.sobreNome.get()))
			return false;
		return true;
	}

}
