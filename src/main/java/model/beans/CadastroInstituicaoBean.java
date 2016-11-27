package model.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import persistence.crud.InstituicaoDAO;
import persistence.pojo.Instituicao;

@ManagedBean(name = "cadastroInstituicaoBean")
@ViewScoped
public class CadastroInstituicaoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2653017008497678961L;
	
	private Instituicao instituicao = new Instituicao();
	private InstituicaoDAO objInstituicaoDAO = new InstituicaoDAO();

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	
	public String inserir() throws Exception{
		//Tem que entrar algum tipo de validação aki se o js não funcionar
		if(objInstituicaoDAO.consulta(instituicao.getAbreviacao())){
			objInstituicaoDAO.inserirInstituicao(instituicao);
			return "sucesso";
			
		}
		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", "Instituição ja cadastrada!"));
		
		return "erro";
	}
	
	public String cancelar(){
		return "cancelar";
	}
	
	
}
