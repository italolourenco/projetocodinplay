package model.beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import persistence.crud.AtividadeDAO;
import persistence.crud.TarefaDAO;
import persistence.pojo.Atividade;
import persistence.pojo.Tarefa;
import persistence.pojo.Usuario;

@ManagedBean(name = "resolucaoAtividadeBean")
@ViewScoped
public class ResolucaoAtividadeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8902559209257287941L;
	
	private ArrayList<Atividade> listAtividades = new ArrayList<Atividade>();
	
	private AtividadeDAO objAtividadeDAO;
	private TarefaDAO objTarefaDAO;
	private Tarefa tarefa;
	private Atividade atividade;
	private int resposta;
	
	
    @ManagedProperty(value = "#{usuario}")
    private Usuario usuario; 
    
	@PostConstruct
	public void init(){
		
		try {
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			usuario = (Usuario)session.getAttribute("identificaUsuario"); 
			
			objAtividadeDAO = new AtividadeDAO();
			objTarefaDAO = new TarefaDAO();
			tarefa = defineTarefa(usuario);
			preparaAtividades();
			defineAtividade();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public Tarefa defineTarefa(Usuario usuario) throws Exception{
		
		tarefa = new Tarefa();
		tarefa = objTarefaDAO.encontraTarefa(usuario);
		return tarefa;
	}
	
	public void preparaAtividades() throws Exception{
		
		listAtividades = objAtividadeDAO.montaHistorico(tarefa, usuario, 1, 1);
		if(listAtividades.size() == 0){
			listAtividades = objAtividadeDAO.preparaAtividadesSemHist(tarefa);
		}
	}
	
	public void defineAtividade(){
		
		this.atividade = listAtividades.get(0);
		listAtividades.remove(0);
	}

	public ArrayList<Atividade> getListAtividades() {
		return listAtividades;
	}

	public void setListAtividades(ArrayList<Atividade> listAtividades) {
		this.listAtividades = listAtividades;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getResposta() {
		return resposta;
	}

	public void setResposta(int resposta) {
		this.resposta = resposta;
	}
	
	
	
	

}
