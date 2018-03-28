package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;

/**
 * 
 * @author RA816124368 Pedro Gallon Alves CCP3AN-MCA1
 *
 */

@Repository
public class ChamadoDAO {
	private Connection conn;

	@Autowired
	public ChamadoDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	/**
	 * 
	 * @param idFila
	 *            = Fila.id selecionado
	 * @return ArrayList de todos os Chamados com Fila.id correspondente
	 * @throws IOException
	 */
	public ArrayList<Chamado> listarChamados(int idFila) throws IOException {
		String query = "SELECT ID_CHAMADO, DESCRICAO, STATUS, DT_ABERTURA, DT_FECHAMENTO, ID_FILA FROM servicedesk.chamado WHERE ID_FILA=?; ";
		ArrayList<Chamado> chamados = new ArrayList<>();
		try (PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setInt(1, idFila);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					Chamado chamado = new Chamado();
					chamado.setId(rs.getInt("ID_CHAMADO"));
					chamado.setDescricao(rs.getString("DESCRICAO"));
					chamado.setStatus(rs.getString("STATUS"));
					chamado.setDataAbertura(rs.getDate("DT_ABERTURA"));
					chamado.setDataFechamento(rs.getDate("DT_FECHAMENTO"));
					chamado.setIdFila(rs.getInt("ID_FILA"));

					chamados.add(chamado);
				}
			}

		} catch (SQLException e) {
			throw new IOException(e);
		}
		return chamados;
	}

	/**
	 * 
	 * @param idFila
	 *            = Fila.id selecionado
	 * @return ArrayList de todos os Chamados Abertos com Fila.id correspondente
	 * @throws IOException
	 */
	public ArrayList<Chamado> listarChamadosAbertos(int idFila) throws IOException {
		String query = "SELECT ID_CHAMADO, DESCRICAO, STATUS, DT_ABERTURA, DT_FECHAMENTO, ID_FILA FROM servicedesk.chamado WHERE ID_FILA=? AND STATUS = 'aberto'; ";
		ArrayList<Chamado> chamados = new ArrayList<>();
		try (PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setInt(1, idFila);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					Chamado chamado = new Chamado();
					chamado.setId(rs.getInt("ID_CHAMADO"));
					chamado.setDescricao(rs.getString("DESCRICAO"));
					chamado.setStatus(rs.getString("STATUS"));
					chamado.setDataAbertura(rs.getDate("DT_ABERTURA"));
					chamado.setDataFechamento(rs.getDate("DT_FECHAMENTO"));
					chamado.setIdFila(rs.getInt("ID_FILA"));

					chamados.add(chamado);
				}
			}

		} catch (SQLException e) {
			throw new IOException(e);
		}
		return chamados;
	}

	// public Chamado carregarChamado(int id) throws IOException {
	// String query = "SELECT ID_CHAMADO, DESCRICAO, STATUS, DT_ABERTURA,
	// DT_FECHAMENTO, ID_FILA FROM servicedesk.chamado WHERE ID_CHAMADO = ?; ";
	// Chamado chamado = new Chamado();
	// try(Connection conn = ConnectionFactory.getConnection();
	// PreparedStatement pst = conn.prepareStatement(query);
	// ){
	// pst.setInt(1, id);
	// try(ResultSet rs = pst.executeQuery();){
	// while(rs.next()) {
	//
	// chamado.setId(rs.getInt("ID_CHAMADO"));
	// chamado.setDescricao(rs.getString("DESCRICAO"));
	// chamado.setStatus(rs.getString("STATUS"));
	// chamado.setDataAbertura(rs.getDate("DT_ABERTURA"));
	// chamado.setDataFechamento(rs.getDate("DT_FECHAMENTO"));
	// chamado.setIdFila(rs.getInt("ID_FILA"));
	// }
	// }
	// }catch (SQLException e) {
	// throw new IOException(e);
	// }
	// return chamado;
	// }

	/**
	 * 
	 * @param chamado
	 *            = Chamado com descricao e idFila Criação de entrada no banco com
	 *            descrição, status = aberto, dt_abertura = data atual e idFila
	 * @throws IOException
	 */
	public void criarChamado(Chamado chamado) throws IOException {
		String query = "INSERT INTO CHAMADO (DESCRICAO, STATUS, DT_ABERTURA, ID_FILA) VALUE(?, ?, ?, ?); ";
		try (PreparedStatement pst = conn.prepareStatement(query);) {
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

			pst.setString(1, chamado.getDescricao());
			pst.setString(2, "aberto");
			pst.setTimestamp(3, date);
			pst.setInt(4, chamado.getIdFila());

			pst.execute();

		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	/**
	 * 
	 * @param ids
	 *            = array de ID's de Chamados a serem fechados Update status =
	 *            fechado e dt_fechamento = data atual
	 * @throws IOException
	 */
	public void fecharChamado(int ids[]) throws IOException {
		String setup = "";
		for (int i = 0; i < ids.length; i++) {
			setup += ids[i];
			if (i < ids.length - 1)
				setup += ",";
		}

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		String query = "UPDATE servicedesk.chamado SET STATUS='fechado', DT_FECHAMENTO=? WHERE ID_CHAMADO IN (" + setup
				+ ");";
		try (PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setTimestamp(1, date);

			pst.execute();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

}
