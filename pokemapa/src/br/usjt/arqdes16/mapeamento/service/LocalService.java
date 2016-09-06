package br.usjt.arqdes16.mapeamento.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqdes16.mapeamento.dao.LocalDAO;
import br.usjt.arqdes16.mapeamento.model.Local;

@Service
public class LocalService {
	private LocalDAO dao;
		
	@Autowired
	public LocalService(LocalDAO dao) {
		this.dao = dao;
	}
	
	
	public Local criar(Local local) throws IOException{
		return dao.criar(local);
	}	
	public void remover(Local local) throws IOException{
		dao.excluir(local);
	}
	public void atualizar(Local local) throws IOException{
		dao.atualizar(local);
	}
	public ArrayList<Local> listarLocais() throws IOException{
		return dao.listarLocais();
	}
	public ArrayList<Local> listarLocais(String chave) throws IOException {
		return dao.listarLocais(chave);
	}
	public Local mostrar(Local local) throws IOException {
		return dao.selecionar(local);
	}
}
