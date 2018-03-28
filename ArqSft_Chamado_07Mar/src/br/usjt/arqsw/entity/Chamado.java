package br.usjt.arqsw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
/**
 * 
 * @author RA816124368 Pedro Gallon Alves CCP3AN-MCA1
 *
 */
public class Chamado implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String ABERTO = "aberto";
	public static final String FECHADO = "fechado";
	
	private int id;
	@NotNull
	private int idFila;
	@NotNull
	private String descricao;
	private String status;
	private Date dataAbertura;
	private Date dataFechamento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Date getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public static String getAberto() {
		return ABERTO;
	}
	public static String getFechado() {
		return FECHADO;
	}
	
	public int getIdFila() {
		return idFila;
	}
	public void setIdFila(int idFila) {
		this.idFila = idFila;
	}
	public Chamado() {
		super();
	}
	@Override
	public String toString() {
		return "Chamado [id=" + id + ", idFila=" + idFila + ", descricao=" + descricao + ", status=" + status
				+ ", dataAbertura=" + dataAbertura + ", dataFechamento=" + dataFechamento + "]";
	}
	


	

}
