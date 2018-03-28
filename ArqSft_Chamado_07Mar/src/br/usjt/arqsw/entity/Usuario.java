package br.usjt.arqsw.entity;

import javax.validation.constraints.NotNull;

public class Usuario {
	@NotNull
	private String id;
	@NotNull
	private String pw;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public Usuario() {
		super();
	}
	public Usuario(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", pw=" + pw + "]";
	}
	
	
	
	

}
