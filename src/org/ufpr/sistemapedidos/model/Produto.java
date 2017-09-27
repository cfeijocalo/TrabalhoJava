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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		Produto other = (Produto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.get().equals(other.descricao.get()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!(id.get() == other.id.get()))
			return false;
		return true;
	}

}
