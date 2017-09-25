package org.ufpr.sistemapedidos.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Caio Calo
 */
public class Produto {

	private final IntegerProperty id;
	private final StringProperty descricao;

	public Produto() {
		this(0, null);
	}

	public Produto(Integer id, String descricao) {
		this.id = new SimpleIntegerProperty(id);
		this.descricao = new SimpleStringProperty(descricao);
	}

	// GETTERS AND SETTERS
	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public String getDescricao() {
		return descricao.get();
	}

	public void setDescricao(String descricao) {
		this.descricao.set(descricao);
	}

	public StringProperty descricaoProperty() {
		return descricao;
	}

}
