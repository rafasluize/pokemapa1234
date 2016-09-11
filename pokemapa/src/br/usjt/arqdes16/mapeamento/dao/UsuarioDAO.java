package br.usjt.arqdes16.mapeamento.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.cfg.annotations.ResultsetMappingSecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqdes16.mapeamento.model.Usuario;

@Repository
public class UsuarioDAO {
	private Connection conn;
	@Autowired
	public UsuarioDAO(DataSource dataSource) throws IOException{
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public Boolean validar(Usuario usuario) throws IOException {
		String sql = "SELECT username, password FROM usuario where username = ?";
		try(PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, usuario.getUsername());
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()){
					if(rs.getString("password").equals(usuario.getPassword())){
						return true; 
					}
				}
			}
			catch (SQLException e){
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
