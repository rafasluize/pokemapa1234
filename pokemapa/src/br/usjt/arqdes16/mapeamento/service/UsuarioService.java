package br.usjt.arqdes16.mapeamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqdes16.mapeamento.dao.UsuarioDAO;
import br.usjt.arqdes16.mapeamento.model.Usuario;

@Service
public class UsuarioService {
	private UsuarioDAO dao;
	
	@Autowired
	public UsuarioService(UsuarioDAO dao){
		this.dao = dao; 
	}

	public Usuario validar(Usuario usuario) {
		// TODO Auto-generated method stub
		return dao.validar(usuario); 
	}
	
	

}
