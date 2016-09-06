package br.usjt.arqdes16.mapeamento.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

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
	public Usuario validar(Usuario usuario) {
		// TODO Auto-generated method stub
		
		return usuario;
	}
}
