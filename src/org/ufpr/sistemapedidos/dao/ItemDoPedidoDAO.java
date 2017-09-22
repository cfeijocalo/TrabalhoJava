package org.ufpr.sistemapedidos.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ufpr.sistemapedidos.dao.generic.GenericDAO;
import org.ufpr.sistemapedidos.model.ItemDoPedido;
import org.ufpr.sistemapedidos.util.ConnectionFactory;

/**
 * 
 * @author Caio Calo
 */
public class ItemDoPedidoDAO implements GenericDAO<ItemDoPedido> {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	@Override
	public ItemDoPedido selectById(Long id) throws SQLException {
		ItemDoPedido item = null;
		String query = "SELECT * FROM item_pedido WHERE id = " + id + ";";

		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			ProdutoDAO pDao = new ProdutoDAO();
			if (rs.next()) {
				item = new ItemDoPedido(rs.getInt("quantidade"), 
						pDao.selectById(rs.getLong("id_produto")));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}

		return item;
	}

	@Override
	public List<ItemDoPedido> selectAll(String condition) throws SQLException {
		List<ItemDoPedido> itens = new ArrayList<ItemDoPedido>();
		String query = "SELECT * FROM item_pedido ";
		
		if (condition != null && !condition.isEmpty()) {
			query = query.concat(condition);
		}

		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			ProdutoDAO pDao = new ProdutoDAO();
			while (rs.next()) {
				ItemDoPedido item = new ItemDoPedido(rs.getInt("quantidade"), 
						pDao.selectById(rs.getLong("id_produto")));
				itens.add(item);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
			rs.close();
			stmt.close();
			conn.close();
		}

		return itens;
	}

	/**
	 * <b>NÃO UTILIZAR!!!</b>
	 */
	@Deprecated
	@Override
	public boolean insert(ItemDoPedido entity) throws SQLException {
		return false;
	}
	
	/**
	 * Insere a entity no banco, necessário o id do pedido.
	 * 
	 * @author Caio Calo
	 * @param idPedido
	 * @param entity
	 * @return <b>true</b> - se inserido com sucesso.<br>
	 *         <b>false</b> - se houve algum problema na inserção. 
	 * @throws SQLException
	 */
	public boolean insert(int idPedido, ItemDoPedido entity) throws SQLException {
		boolean result = false;
		String query = "INSERT INTO item_pedido(id_pedido, id_produto, quantidade) VALUES (?, ?, ?);";

		try {
			conn = ConnectionFactory.create();
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, idPedido);
			stmt.setInt(2, entity.getProduto().getId());
			stmt.setInt(3, entity.getQuantidade());
			
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

	/**
	 * <b>NÃO UTILIZAR!!!</b>
	 */
	@Deprecated
	@Override
	public boolean update(ItemDoPedido entity) throws SQLException {
		return false;
	}
	
	/**
	 * Atualiza os dados no banco, necessário passar o id do pedido.
	 * 
	 * @author Caio Calo
	 * @param idPedido
	 * @param entity
	 * @return <b>true</b> - se atualizado com sucesso.<br>
	 *         <b>false</b> - se houve algum problema na atualização. 
	 * @throws SQLException
	 */
	public boolean update(int idPedido, ItemDoPedido entity) throws SQLException {
		boolean result = false;
		String query = "UPDATE item_pedido SET quantidade=? WHERE id_pedido = AND id_produto = ";

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
	
	/**
	 * <b>NÃO UTILIZAR!!!</b>
	 */
	@Deprecated
	@Override
	public boolean delete(ItemDoPedido entity) throws SQLException {
		return false;
	}
	
	/**
	 * 
	 * @author Caio Calo
	 * @param idPedido
	 * @param entity
	 * @return <b>true</b> - se removido com sucesso.<br>
	 *         <b>false</b> - se houve algum problema na remoção. 
	 * @throws SQLException
	 */
	public boolean delete(int idPedido) throws SQLException {
		boolean result = false;
		String query = "DELETE FROM item_pedido WHERE id_pedido = " + idPedido +" ;";

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
