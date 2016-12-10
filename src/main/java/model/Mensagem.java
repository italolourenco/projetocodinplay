package model;

import java.io.Serializable;

public class Mensagem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3391821390458997431L;
	private Integer id_mensagem;
	private String data;
	private String mensagem;
	private Usuario objUsuario;
	
	
	
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Integer getId_mensagem() {
		return id_mensagem;
	}
	public void setId_mensagem(Integer id_mensagem) {
		this.id_mensagem = id_mensagem;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Usuario getObjUsuario() {
		return objUsuario;
	}
	public void setObjUsuario(Usuario objUsuario) {
		this.objUsuario = objUsuario;
	}
	
	
	

}
