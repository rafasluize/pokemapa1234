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

import br.usjt.arqdes16.mapeamento.model.Cidade;

@Repository
public class CidadeDAO {
	private Connection conn;

	@Autowired
	public CidadeDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	public ArrayList<Cidade> selecionarTodas() throws IOException {
		String query = "select idCidade, nmCidade, u.idUf from cidade c, uf u" + " where c.idUf = u.idUf";
		ArrayList<Cidade> lista = new ArrayList<>();
		Cidade cidade;
		try (PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				cidade = new Cidade();
				cidade.setId(rs.getInt("idCidade"));
				cidade.setNome(rs.getString("nmCidade"));
				cidade.setUf(rs.getString("idUf"));
				lista.add(cidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}
}
