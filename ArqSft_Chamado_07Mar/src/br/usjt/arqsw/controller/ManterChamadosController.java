package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.entity.Usuario;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;
import br.usjt.arqsw.service.UsuarioService;

/**
 * 
 * @author RA816124368 Pedro Gallon Alves CCP3AN-MCA1
 *
 */
@Controller
public class ManterChamadosController {
	private FilaService filaService;
	private ChamadoService chamadoService;
	private UsuarioService usuarioService;

	@Autowired
	public ManterChamadosController(FilaService filaService, ChamadoService chamadoService,
			UsuarioService usuarioService) {
		this.filaService = filaService;
		this.chamadoService = chamadoService;
		this.usuarioService = usuarioService;
	}

	/**
	 * 
	 * @return index
	 */
	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	private List<Fila> listarFilas() throws IOException {
		return filaService.listarFilas();
	}

	private ArrayList<Chamado> listarChamados(int idFila) throws IOException {
		return chamadoService.listarChamados(idFila);
	}

	private ArrayList<Chamado> listarChamadosAbertos(int idFila) throws IOException {
		return chamadoService.listarChamadosAbertos(idFila);
	}

	private void fecharChamado(int ids[]) throws IOException {
		chamadoService.fecharChamado(ids);
	}

	private Usuario logarUsuario(Usuario usuario) throws IOException {
		return usuarioService.logarUsuario(usuario);
	}

	/**
	 * 
	 * @param model
	 *            * Acesso à request http
	 * @return JSP de escolher Fila para listar Chamados
	 */
	@RequestMapping("/fila_consultar")
	public String listarFilasExibir(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	/**
	 * 
	 * @param model
	 * @return JSP de escolher Fila para listar Chamados Abertos
	 */
	@RequestMapping("/chamado_aberto_escolher")
	public String escolherChamadoAberto(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "chamado_aberto_escolher";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	/**
	 * 
	 * @param selected
	 *            = String contendo id's dos Chamados com checkbox marcado
	 * @param model
	 * @return JSP index, com toast/snackbar de "sucesso" dizendo quais foram as
	 *         id's fechadas
	 */
	@RequestMapping("/chamado_aberto_fechar")
	public String fecharChamadoAberto(@RequestParam(value = "selected", required = true) String selected, Model model) {
		try {
			String strings[] = selected.split(",");
			int ids[] = new int[strings.length];
			for (int i = 0; i < strings.length; i++) {
				ids[i] = Integer.parseInt(strings[i].toString());
			}
			fecharChamado(ids);
			model.addAttribute("snackbarAlert", "Chamado(s) #" + selected + " fechado(s)!");
			return "index";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	/**
	 * 
	 * @param model
	 * @return JSP para escolher Descrição e Fila do novo Chamado
	 */
	@RequestMapping("/chamado_novo")
	public String chamadoCriador(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "chamado_novo";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	/**
	 * 
	 * @param chamado
	 *            = Chamado carregando Descrição e Fila
	 * @param result
	 * @param model
	 *            = texto do snackbar definido
	 * @return JSP index, com toast/snackbar de "sucesso"
	 */
	@RequestMapping("/chamado_criar")
	public String chamadoCriar(@Valid Chamado chamado, BindingResult result, Model model) {
		try {
			System.out.println(chamado.toString());
			chamadoService.criarChamado(chamado);
			model.addAttribute("snackbarAlert", "Chamado criado!");
			System.out.println("chamadoCriar : " + chamado.toString());
			return "index";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	/**
	 * 
	 * @param fila
	 *            = Fila contendo id escolhida
	 * @param result
	 * @param model
	 *            = lista de Chamados, Fila.nome
	 * @return JSP com tabela listando Chamados
	 */
	@RequestMapping("/chamado_listar")
	public String chamadoListar(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoListar";
			}

			fila = filaService.carregarFila(fila.getId());
			model.addAttribute("fila", fila);

			System.out.println("chamadoListar : " + fila.toString());

			ArrayList<Chamado> listaChamados = new ArrayList<>();
			listaChamados = listarChamados(fila.getId());

			model.addAttribute("listaChamados", listaChamados);
			return "chamado_listar";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	/**
	 * 
	 * @param fila
	 *            = Fila contendo id escolhido
	 * @param result
	 * @param model
	 *            lista de Chamados Abertos, Fila.nome
	 * @return JSP com tabela listando Chamados Abertos, candidatos a serem fechados
	 */
	@RequestMapping("/chamado_aberto_listar")
	public String chamadoAbertoListar(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoListar";
			}

			fila = filaService.carregarFila(fila.getId());
			model.addAttribute("fila", fila);

			System.out.println("chamadoListar : " + fila.toString());

			ArrayList<Chamado> listaChamados = new ArrayList<>();
			listaChamados = listarChamadosAbertos(fila.getId());

			model.addAttribute("listaChamados", listaChamados);
			return "chamado_aberto_listar";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/login")
	public String login(Model model) {
			return "usuario_logar";
	}

	@RequestMapping("/usuario_logar")
	public String usuarioLogar(@Valid Usuario usuario, BindingResult result, Model model, HttpSession session) {
		try {

			Usuario u = logarUsuario(usuario);
			if (u != null) {
				
				System.out.println("usuarioLogar : " + usuario.toString());
				session.setAttribute("usuarioLogado", u);
				return "index";
			}
			
			model.addAttribute("snackbarAlert", "Login inv�lido!");
			return "usuario_logar";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

}
