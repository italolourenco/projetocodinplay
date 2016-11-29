package model.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import persistence.crud.UsuarioDAO;

import java.io.Serializable;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2367255307429177002L;
	
	private String email;
	private String nome;
	
	private UsuarioDAO objUsuarioDAO = new UsuarioDAO();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String cadastro(){
		
		return "cadastro";
	}
	
	public String entrar(){
		
		return "entrar";
	}

}
