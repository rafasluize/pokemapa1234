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

import br.usjt.arqdes16.mapeamento.model.Local;

@Repository
public class LocalDAO {
	private Connection conn;

	@Autowired
	public LocalDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	public Local criar(Local local) throws IOException {
		String sqlInsert = "insert into local (nmlocal, latitude, longitude, idCidade, idTipoLocal) "
				+ "values(?,?,?,?,?)";
		try (PreparedStatement pst = conn.prepareStatement(sqlInsert);) {
			pst.setString(1, local.getNome());
			pst.setDouble(2, local.getLatitude());
			pst.setDouble(3, local.getLongitude());
			pst.setInt(4, local.getCidade());
			pst.setInt(5, local.getTipo());
			pst.execute();
			String query = "select last_insert_id()";
			try (PreparedStatement pst2 = conn.prepareStatement(query); ResultSet rs = pst2.executeQuery();) {
				if (rs.next()) {
					local.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return local;
	}

	public void atualizar(Local local) throws IOException {
		String sqlUpdate = "update local set nmlocal=?, latitude=?, longitude=?, "
				+ "idCidade=?, idTipoLocal=? where idlocal=?";
		try (PreparedStatement pst = conn.prepareStatement(sqlUpdate);) {
			pst.setString(1, local.getNome());
			pst.setDouble(2, local.getLatitude());
			pst.setDouble(3, local.getLongitude());
			pst.setInt(4, local.getCidade());
			pst.setInt(5, local.getTipo());
			pst.setInt(6, local.getId());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}

	public void excluir(Local local) throws IOException {
		String sqlDelete = "delete from local where idlocal=?";
		try (PreparedStatement pst = conn.prepareStatement(sqlDelete);) {
			pst.setInt(1, local.getId());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}

	public Local selecionar(Local local) throws IOException {
		String query = "select l.idlocal, l.nmlocal, l.latitude, l.longitude, "
				+ "l.idCidade, c.nmCidade, c.idUf, l.idTipoLocal, t.nmTipoLocal "
				+ "from local l, cidade c, tipolocal t where l.idCidade = c.idCidade "
				+ "and l.idTipoLocal = t.idTipoLocal and l.idlocal=?";
		try (PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setInt(1, local.getId());
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					local.setNome(rs.getString("nmlocal"));
					local.setLatitude(rs.getDouble("latitude"));
					local.setLongitude(rs.getDouble("longitude"));
					local.setCidade(rs.getInt("idCidade"));
					local.setTipo(rs.getInt("idTipoLocal"));
					local.setNomeCidade(rs.getString("nmCidade") + " - " + rs.getString("idUf"));
					local.setNomeTipo(rs.getString("nmTipoLocal"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return local;
	}

	public ArrayList<Local> listarLocais() throws IOException {
		String query = "select l.idlocal, l.nmlocal, l.latitude, l.longitude, "
				+ "l.idCidade, c.nmCidade, c.idUf, l.idTipoLocal, t.nmTipoLocal "
				+ "from local l, cidade c, tipolocal t where l.idCidade = c.idCidade "
				+ "and l.idTipoLocal = t.idTipoLocal order by l.nmlocal";
		ArrayList<Local> lista = new ArrayList<>();
		try (PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				Local local = new Local();
				local.setId(rs.getInt("idlocal"));
				local.setNome(rs.getString("nmlocal"));
				local.setLatitude(rs.getDouble("latitude"));
				local.setLongitude(rs.getDouble("longitude"));
				local.setCidade(rs.getInt("idCidade"));
				local.setTipo(rs.getInt("idTipoLocal"));
				local.setNomeCidade(rs.getString("nmCidade") + " - " + rs.getString("idUf"));
				local.setNomeTipo(rs.getString("nmTipoLocal"));
				lista.add(local);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}

	public ArrayList<Local> listarLocais(String chave) throws IOException {
		String query = "select l.idlocal, l.nmlocal, l.latitude, l.longitude, "
				+ "l.idCidade, c.nmCidade, c.idUf, l.idTipoLocal, t.nmTipoLocal "
				+ "from local l, cidade c, tipolocal t where l.idCidade = c.idCidade "
				+ "and l.idTipoLocal = t.idTipoLocal and l.nmlocal like ? order by l.nmlocal";
		ArrayList<Local> lista = new ArrayList<>();
		try (PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setString(1, "%" + chave + "%");
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					Local local = new Local();
					local.setId(rs.getInt("idlocal"));
					local.setNome(rs.getString("nmlocal"));
					local.setLatitude(rs.getDouble("latitude"));
					local.setLongitude(rs.getDouble("longitude"));
					local.setCidade(rs.getInt("idCidade"));
					local.setTipo(rs.getInt("idTipoLocal"));
					local.setNomeCidade(rs.getString("nmCidade") + " - " + rs.getString("idUf"));
					local.setNomeTipo(rs.getString("nmTipoLocal"));
					lista.add(local);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}
}
