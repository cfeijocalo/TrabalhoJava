package org.ufpr.sistemapedidos.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ufpr.sistemapedidos.dao.generic.GenericDAO;
import org.ufpr.sistemapedidos.model.Pedido;
import org.ufpr.sistemapedidos.util.ConnectionFactory;

/**
 * 
 * @author Caio Calo
 */
public class PedidoDAO implements GenericDAO<Pedido> {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	@Override
	public Pedido selectById(Long id) throws SQLException {
		Pedido pedido = null;
		String query = "SELECT * FROM pedido WHERE id = " + id + ";";

		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			ClienteDAO cDao = new ClienteDAO();
			ItemDoPedidoDAO idpDao = new ItemDoPedidoDAO();
			if (rs.next()) {
				pedido = new Pedido(rs.getInt("id"), 
						rs.getDate("date"), 
						cDao.selectById(rs.getLong("id_cliente")), 
						idpDao.selectAll("WHERE id_pedido = " + id + ";"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}

		return pedido;
	}

	@Override
	public List<Pedido> selectAll(String condition) throws SQLException {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		String query = "SELECT * FROM pedido ";
		
		if (condition != null && !condition.isEmpty()) {
			query = query.concat(condition);
		}

		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			ClienteDAO cDao = new ClienteDAO();
			ItemDoPedidoDAO idpDao = new ItemDoPedidoDAO();
			while (rs.next()) {
				Pedido pedido = new Pedido(rs.getInt("id"), 
						rs.getDate("date"), 
						cDao.selectById(rs.getLong("id_cliente")), 
						idpDao.selectAll("WHERE id_pedido = " + rs.getInt("id")));
				pedidos.add(pedido);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}

		return pedidos;
	}

	@Override
	public boolean insert(Pedido entity) throws SQLException {
		boolean result = false;
		String query = "INSERT INTO public.pedido(data, id_cliente) VALUES (?, ?);";
		
		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			
			stmt.setDate(1, new Date(System.currentTimeMillis()));
			stmt.setInt(2, entity.getCliente().getId());
			
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
	public boolean update(Pedido entity) throws SQLException {
		boolean result = false;
		String query = "UPDATE public.pedido SET data=?, id_cliente=? WHERE id = " + entity.getId() + ";";
		
		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			
			stmt.setDate(1, (java.sql.Date) entity.getData());
			stmt.setInt(2, entity.getCliente().getId());
			
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
	public boolean delete(Pedido entity) throws SQLException {
		boolean result = false;
		String query = "DELETE FROM public.pedido WHERE id = " + entity.getId() + ";";
		
		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			
			ItemDoPedidoDAO idpDao = new ItemDoPedidoDAO();
			if (idpDao.delete(entity.getId())) { 
				if (stmt.execute()) {
					result = true;
				}
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
