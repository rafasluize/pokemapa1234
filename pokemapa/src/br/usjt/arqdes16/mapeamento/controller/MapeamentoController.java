package br.usjt.arqdes16.mapeamento.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqdes16.mapeamento.model.Cidade;
import br.usjt.arqdes16.mapeamento.model.Local;
import br.usjt.arqdes16.mapeamento.model.Tipo;
import br.usjt.arqdes16.mapeamento.model.Usuario;
import br.usjt.arqdes16.mapeamento.service.CidadeService;
import br.usjt.arqdes16.mapeamento.service.LocalService;
import br.usjt.arqdes16.mapeamento.service.TipoService;
import br.usjt.arqdes16.mapeamento.service.UsuarioService;

@Controller
public class MapeamentoController {

	LocalService ls;
	TipoService ts;
	CidadeService cs;
	UsuarioService us;

	@Autowired
	public MapeamentoController(LocalService ls, TipoService ts, CidadeService cs, UsuarioService us) {
		this.ls = ls;
		this.ts = ts;
		this.cs = cs;
		this.us = us;
	}

	@RequestMapping(value = {"","index"})
	public String home() {
		System.out.println("inicio");
		return "redirect:listar_locais";
	}

	@RequestMapping("novo_local")
	public String form(Model model) {
		try {
			ArrayList<Tipo> tipos = ts.listarTipos();
			model.addAttribute("tipos", tipos);
			ArrayList<Cidade> cidades = cs.listarCidades();
			model.addAttribute("cidades", cidades);
			return "local/localcriar";

		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
		}
		return "erro";
	}

	@RequestMapping("alterar_local")
	public String formAlterar(Model model, Local local) {
		try {
			ArrayList<Tipo> tipos = ts.listarTipos();
			model.addAttribute("tipos", tipos);
			ArrayList<Cidade> cidades = cs.listarCidades();
			model.addAttribute("cidades", cidades);
			model.addAttribute("local", ls.mostrar(local));
			return "local/localalterar";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
		}
		return "erro";
	}

	@RequestMapping("incluir_local")
	public String inclusao(@Valid Local local, BindingResult result, Model model) {
		Local localAntes = local; 
		try {
			if (result.hasErrors()) {
				if(result.hasFieldErrors("nome")) local.setNome("");
				if(result.hasFieldErrors("latitude")) localAntes.setLatitude(0.00);
				if(result.hasFieldErrors("longitude")) localAntes.setLongitude(0.00);
				ArrayList<Tipo> tipos = ts.listarTipos();
				model.addAttribute("tipos", tipos);
				ArrayList<Cidade> cidades = cs.listarCidades();
				model.addAttribute("cidades", cidades);
				model.addAttribute("locais",localAntes);
				return "local/localcriar";
			}
			ls.criar(local);
			return "redirect:listar_locais";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
		}
		return "erro";
	}

	@RequestMapping("listar_locais")
	public String listagem(Model model, String chave) {
		try {
			if (chave == null || chave.equals("")) {
				model.addAttribute("locais", ls.listarLocais());
			} else {
				model.addAttribute("locais", ls.listarLocais(chave));
			}
			return "local/locallistar";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
		}
		return "erro";
	}

	@RequestMapping("limpar_locais")
	public String limparListagem(Model model) {
		model.addAttribute("locais", null);
		return "local/locallistar";
	}

	@RequestMapping("mostrar_local")
	public String mostrar(Local local, Model model) {
		try {
			ls.mostrar(local);
			return "local/localmostrar";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
		}
		return "erro";
	}

	@RequestMapping("remover_local")
	public String remover(Local local, Model model) {
		try {
			ls.remover(local);
			return "redirect:listar_locais";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
		}
		return "erro";
	}

	@RequestMapping("atualizar_local")
	public String atualizar(Local local, Model model) {
		try {
			ls.atualizar(local);
			return "redirect:listar_locais";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
		}
		return "erro";
	}
	
	
	@RequestMapping("login")
	public String entrar(Usuario usuario, Model model) {
		return "tela_login";
	}
	
	@RequestMapping("fazer_login")
	public String entrar(Usuario usuario, Model model, HttpServletRequest request) {
		try {
			System.out.println("Login");
			if (us.validar(usuario) == true) {
				System.out.println("logado");
				HttpSession session = request.getSession(); 
				session.setAttribute("logado", true);
				return "redirect:listar_locais";
			}
			System.out.println("login invalido");
			return "tela_login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
		}
		return "erro";
	}
	
	@RequestMapping("deslogar")
	public String deslogar(HttpSession session){
		session.invalidate();
		
		return "tela_login"; 
	}

}
