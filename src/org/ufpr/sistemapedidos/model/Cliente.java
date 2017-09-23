package org.ufpr.sistemapedidos.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {

	private final IntegerProperty id;
	private final StringProperty cpf;
	private final StringProperty nome;
	private final StringProperty sobreNome;
	
	public Cliente() {
		this(null, null, null, null);
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

}
