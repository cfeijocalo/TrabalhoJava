package org.ufpr.sistemapedidos.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ufpr.sistemapedidos.dao.generic.GenericDAO;
import org.ufpr.sistemapedidos.model.Cliente;
import org.ufpr.sistemapedidos.util.ConnectionFactory;

/**
 * 
 * @author Caio Calo
 */
public class ClienteDAO implements GenericDAO<Cliente> {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	@Override
	public Cliente selectById(Long id) throws SQLException {
		Cliente cliente = null;
		String query = "SELECT * FROM cliente WHERE id = " + id + ";";

		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			if (rs.next()) {
				cliente = new Cliente(rs.getInt("id"), 
						rs.getString("cpf"), 
						rs.getString("nome"),
						rs.getString("sobrenome"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}

		return cliente;
	}

	@Override
	public List<Cliente> selectAll(String condition) throws SQLException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		String query = "SELECT * FROM cliente ";
		
		if (condition != null && !condition.isEmpty()) {
			query = query.concat(condition);
		}

		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente(rs.getInt("id"), 
						rs.getString("cpf"), 
						rs.getString("nome"),
						rs.getString("sobrenome"));
				clientes.add(cliente);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
			rs.close();
			stmt.close();
			conn.close();
		}

		return clientes;
	}

	@Override
	public boolean insert(Cliente entity) throws SQLException {
		boolean result = false;
		String query = "INSERT INTO cliente(cpf, nome, sobrenome) VALUES (?, ?, ?);";
		
		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, entity.getCpf());
			stmt.setString(2, entity.getNome());
			stmt.setString(3, entity.getSobreNome());
			
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
	public boolean update(Cliente entity) throws SQLException {
		boolean result = false;
		String query = "UPDATE cliente SET cpf=?, nome=?, sobrenome=? WHERE id = " + entity.getId() + ";";
		
		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, entity.getCpf());
			stmt.setString(2, entity.getNome());
			stmt.setString(3, entity.getSobreNome());
			
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
	public boolean delete(Cliente entity) throws SQLException {
		boolean result = false;
		String query = "DELETE FROM public.cliente WHERE id = " + entity.getId() + ";";
		
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
