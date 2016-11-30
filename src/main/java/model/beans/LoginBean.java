package model.beans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import persistence.crud.UsuarioDAO;
import persistence.pojo.Usuario;

import java.io.Serializable;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2367255307429177002L;
	
	private String email;
	private String senha;
	
	@ManagedProperty(value = "#{usuario}")
	private Usuario usuario;
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	private UsuarioDAO objUsuarioDAO = new UsuarioDAO();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String cadastro(){
		
		return "cadastro";
	}
	
	public String entrar() throws Exception{
		
		if(this.email.equals("") || this.senha.equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", "Os campos são obrigatorios!"));
			return "erro";
		}
		else{
			if(!objUsuarioDAO.consultaEmail(this.email)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", "Usuario não encotrado!"));
				return "erro";
			}
			else
			{
				usuario = objUsuarioDAO.consulta(email);
				if(this.senha.equals(usuario.getSenha())){
			        FacesContext fc = FacesContext.getCurrentInstance();
			        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			        session.setAttribute("identificaUsuario", usuario);
					if(usuario.getTipo() == 1)
						return "entrar";
					else
						return "admin";
				}
				else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", "Usuario ou senha incorretos!"));
					return "erro";
				}
				
			}
		}
	}
	
}
