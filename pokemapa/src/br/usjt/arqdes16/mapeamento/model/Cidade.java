package br.usjt.arqdes16.mapeamento.model;

import java.io.Serializable;

public class Cidade implements Serializable{
	private static final long serialVersionUID = 1L;
	public int id;
	public String nome;
	public String uf;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", nome=" + nome + ", uf=" + uf + "]";
	}
}
