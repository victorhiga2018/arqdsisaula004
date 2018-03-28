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

import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author RA816124368 Pedro Gallon Alves CCP3AN-MCA1
 *
 */
@Repository
public class FilaDAO {
	private Connection conn;

	@Autowired
	public FilaDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
	
	/**
	 * 
	 * @return ArryList com todas as filas
	 * @throws IOException
	 */
	public ArrayList<Fila> listarFilas() throws IOException {
		String query = "select id_fila, nm_fila from fila";
		ArrayList<Fila> lista = new ArrayList<>();
		try(PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();){
			
			while(rs.next()) {
				Fila fila = new Fila();
				fila.setId(rs.getInt("id_fila"));
				fila.setNome(rs.getString("nm_fila"));
				lista.add(fila);
			}
			
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return lista;
	}
	
	/**
	 * 
	 * @param id = id da fila a ser carregada
	 * @return	Fila preenchida
	 * @throws IOException
	 */
	public Fila carregarFila(int id) throws IOException {
		String query = "SELECT ID_FILA, NM_FILA FROM fila WHERE ID_FILA = ?; ";
		Fila fila = new Fila();
		try(PreparedStatement pst = conn.prepareStatement(query);				){
			pst.setInt(1, id);
			try(ResultSet rs = pst.executeQuery();){
				while(rs.next()) {
					
					fila.setId(rs.getInt("ID_FILA"));
					fila.setNome(rs.getString("NM_FILA"));
				}
			}
		}catch (SQLException e) {
			throw new IOException(e);
		}
		return fila;
	}

}
