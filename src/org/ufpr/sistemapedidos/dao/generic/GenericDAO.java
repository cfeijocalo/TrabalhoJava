package org.ufpr.sistemapedidos.dao.generic;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * @author Caio Calo
 *
 * @param <T> entity
 */
public interface GenericDAO<T> {

	/**
	 * Busca por ID.
	 * 
	 * @author Caio Calo
	 * @param id
	 * @return entity
	 * @throws SQLException
	 */
	T selectById(Long id) throws SQLException;

	/**
	 * Busca todos que atendem a condição.
	 * 
	 * @author Caio Calo
	 * @param condition
	 * @return entity
	 * @throws SQLException
	 */
	List<T> selectAll(String condition) throws SQLException;

	/**
	 * Insere a entity no banco.
	 * 
	 * @author Caio Calo
	 * @param entity
	 * @return <b>true</b> - se inserido com sucesso.<br>
	 *         <b>false</b> - se houve algum problema na inserção.
	 * @throws SQLException
	 */
	boolean insert(T entity) throws SQLException;

	/**
	 * Atualiza os dados no banco.
	 * 
	 * @author Caio Calo
	 * @param entity
	 * @return <b>true</b> - se atualizado com sucesso.<br>
	 *         <b>false</b> - se houve algum problema na atualização.
	 * @throws SQLException
	 */
	boolean update(T entity) throws SQLException;

	/**
	 * Remove a entity do banco.
	 * 
	 * @author Caio Calo
	 * @param entity
	 * @return <b>true</b> - se removido com sucesso.<br>
	 *         <b>false</b> - se houve algum problema na remoção.
	 * @throws SQLException
	 */
	boolean delete(T entity) throws SQLException;

}
