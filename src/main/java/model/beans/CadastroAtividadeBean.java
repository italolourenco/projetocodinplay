package model.beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import persistence.crud.AtividadeDAO;
import persistence.crud.NivelDAO;
import persistence.crud.TarefaDAO;
import persistence.pojo.Atividade;
import persistence.pojo.Nivel;

@ManagedBean(name = "cadastroAtividadeBean")
@ViewScoped
public class CadastroAtividadeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8520590711208006085L;
	
	private NivelDAO objNivelDAO = new NivelDAO();
	private ArrayList<Nivel> niveis;
	private Integer codNivel = null;
	private Integer codTarefa = null;
	private Atividade atividade;
	
	AtividadeDAO objAtividadeDAO = new AtividadeDAO();
	TarefaDAO objTarefaDAO = new TarefaDAO();

	public Atividade getAtividade() {
		return atividade;
	}


	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}


	public ArrayList<Nivel> getNiveis() {
		return niveis;
	}


	public void setNiveis(ArrayList<Nivel> niveis) {
		this.niveis = niveis;
	}


	public Integer getCodNivel() {
		return codNivel;
	}


	public void setCodNivel(Integer codNivel) {
		this.codNivel = codNivel;
	}


	public Integer getCodTarefa() {
		return codTarefa;
	}


	public void setCodTarefa(Integer codTarefa) {
		this.codTarefa = codTarefa;
	}


	@PostConstruct
	public void init(){
		try {
			atividade = new Atividade();
			niveis = objNivelDAO.consulta();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String cadastroAtividade() throws Exception{
		
		atividade.setObjNivel(objNivelDAO.consulta(codNivel));
		atividade.setObjTarefa(objTarefaDAO.consulta(codTarefa));
		if(!objAtividadeDAO.consulta(this.atividade)){
			
			objAtividadeDAO.inserir(this.atividade);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Atividade Cadastrada !."));
			return "sucesso";
					
				}
				else
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", "Atividade Ja Cadastrada!"));
				
				return "erro";
			}
	
	public String cancelar(){
		return "cancelar";
	}
	}
