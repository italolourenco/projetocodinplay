package model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import persistence.crud.InstituicaoDAO;
import persistence.crud.UsuarioDAO;
import persistence.pojo.Instituicao;
import persistence.pojo.Usuario;


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
	private UsuarioDAO objUsuarioDao = new UsuarioDAO();
	private InstituicaoDAO objInstituicaoDAO = new InstituicaoDAO();
	
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

	public String cadastroPessoa(){
		
		return "sucesso";
	}
	
	public boolean verificarEmail(){
		return true;
	}
}
