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
import persistence.crud.NivelDAO;
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
	private NivelDAO objNivel;
	private Tarefa tarefa;
	
	
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
			objNivel = new NivelDAO();
			tarefa = defineTarefa(usuario);
			listAtividades = objAtividadeDAO.montaHistorico(tarefa, usuario, 1, 1);
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
	
	
	
	

}
