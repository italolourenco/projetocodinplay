package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.*;
import persistence.*;

@ManagedBean(name = "editarBean")
@ViewScoped
public class EditarPerfilBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7904950428097269887L;

	private Usuario usuario;
	private Instituicao instituicao;
	private Nivel nivel = new Nivel();
	private NivelDAO objNivelDAO = new NivelDAO();
	private UsuarioDAO objUsuarioDao = new UsuarioDAO();
	private InstituicaoDAO objInstituicaoDAO = new InstituicaoDAO();
	private PatenteDAO objPatenteDAO = new PatenteDAO();
	private Patente patente = new Patente();
	
	private Integer cod;
	private ArrayList<Instituicao> instituicoes = new ArrayList<Instituicao>();
	
	@PostConstruct
	public void init(){
		
		try {
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			usuario = (Usuario)session.getAttribute("identificaUsuario"); 
			
			instituicoes = objInstituicaoDAO.consulta();
			instituicao = new Instituicao();
			cod = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(ArrayList<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public String updateUsuario() throws Exception{
		
		
		
		//Tem que entrar algum tipo de validação aki se o js não funcionar
		if(!objUsuarioDao.consultaEmail(usuario.getEmail())){
			
			objUsuarioDao.updateUsuario(usuario);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário Atualizado !."));
			return "sucesso";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", "E-mail já cadastrado!"));
			return "erro";
		}
			
	}
	
	public boolean verificarEmail(){
		return true;
	}
	
	public String voltar(){
		return "cancelar";
	}
}
