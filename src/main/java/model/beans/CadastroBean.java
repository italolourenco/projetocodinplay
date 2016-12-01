package model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import persistence.crud.*;
import persistence.pojo.*;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "cadastroBean")
@ViewScoped
public class CadastroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1533075280371625475L;
	
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
			instituicoes = objInstituicaoDAO.consulta();
			usuario = new Usuario();
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

	public String cadastroPessoa() throws Exception{
		
		
		
		//Tem que entrar algum tipo de validação aki se o js não funcionar
		if(!objUsuarioDao.consultaEmail(usuario.getEmail())){
			usuario.setPontuacao(0);
			
			
			nivel = objNivelDAO.consulta(1);
			patente = objPatenteDAO.consultaPatente(1);
			usuario.setTipo(1);
			usuario.setObjPatente(patente);
			usuario.setObjNivel(nivel);
			usuario.setObjInstituicao(objInstituicaoDAO.consulta(this.cod));
			objUsuarioDao.inserirUsuario(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário Cadastrado !."));
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
