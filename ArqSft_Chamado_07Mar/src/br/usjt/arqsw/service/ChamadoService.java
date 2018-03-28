package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;

@Service
public class ChamadoService {
	private ChamadoDAO dao;
	
	@Autowired
	public ChamadoService(ChamadoDAO dao) {
		this.dao = dao;
	}
	public ArrayList<Chamado> listarChamados(int idFila) throws IOException{
		return dao.listarChamados(idFila);
	}
	public ArrayList<Chamado> listarChamadosAbertos(int idFila) throws IOException{
		return dao.listarChamadosAbertos(idFila);
	}
//	public Chamado carregarChamado(int id) throws IOException{
//		return dao.carregarChamado(id);
//	}
	public void criarChamado(Chamado chamado) throws IOException{
		dao.criarChamado(chamado);
	}
	
	public void fecharChamado(int id[]) throws IOException{
		dao.fecharChamado(id);
	}
}
