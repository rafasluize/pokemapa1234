package br.usjt.arqdes16.mapeamento.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqdes16.mapeamento.model.Tipo;

@Repository
public class TipoDAO {
	private Connection conn;

	@Autowired
	public TipoDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	public ArrayList<Tipo> selecionarTodos() throws IOException {
		String query = "select idTipoLocal, nmTipoLocal from tipolocal";
		ArrayList<Tipo> lista = new ArrayList<>();
		Tipo tipo;
		try (PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				tipo = new Tipo();
				tipo.setId(rs.getInt("idTipoLocal"));
				tipo.setNome(rs.getString("nmTipoLocal"));
				lista.add(tipo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}
}
