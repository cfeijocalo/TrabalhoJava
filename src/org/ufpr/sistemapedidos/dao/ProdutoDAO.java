package org.ufpr.sistemapedidos.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ufpr.sistemapedidos.dao.generic.GenericDAO;
import org.ufpr.sistemapedidos.model.Produto;
import org.ufpr.sistemapedidos.util.ConnectionFactory;

/**
 * 
 * @author Caio Calo
 */
public class ProdutoDAO implements GenericDAO<Produto> {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	@Override
	public Produto selectById(Long id) throws SQLException {
		Produto produto = null;
		String query = "SELECT * FROM produto WHERE id = " + id + ";";

		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			if (rs.next()) {
				produto = new Produto(rs.getInt("id"), 
						rs.getString("descricao"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}

		return produto;
	}

	@Override
	public List<Produto> selectAll(String condition) throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();
		String query = "SELECT * FROM produto ";
		
		if (condition != null && !condition.isEmpty()) {
			query = query.concat(condition);
		}

		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			if (rs.next()) {
				Produto produto = new Produto(rs.getInt("id"), 
						rs.getString("descricao"));
				produtos.add(produto);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}

		return produtos;
	}

	@Override
	public boolean insert(Produto entity) throws SQLException {
		boolean result = false;
		String query = "INSERT INTO produto(descricao) VALUES (?);";
		
		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, entity.getDescricao());
			
			if (stmt.execute()) {
				result = true;
			}
				
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			stmt.close();
			conn.close();
		}
		return result;
	}

	@Override
	public boolean update(Produto entity) throws SQLException {
		boolean result = false;
		String query = "UPDATE produto SET descricao=? WHERE id = " + entity.getId() + ";";
		
		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, entity.getDescricao());
			
			if (stmt.execute()) {
				result = true;
			}
				
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			stmt.close();
			conn.close();
		}
		return result;
	}

	@Override
	public boolean delete(Produto entity) throws SQLException {
		boolean result = false;
		String query = "DELETE FROM produto WHERE id = " + entity.getId() + ";";
		
		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			
			if (stmt.execute()) {
				result = true;
			}
				
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			stmt.close();
			conn.close();
		}
		return result;
	}
	
}
