package model.beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import persistence.crud.AtividadeDAO;
import persistence.crud.InstituicaoDAO;
import persistence.crud.TarefaDAO;
import persistence.crud.UsuarioDAO;
import persistence.pojo.Atividade;
import persistence.pojo.Instituicao;
import persistence.pojo.Tarefa;
import persistence.pojo.Usuario;



@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8113681675430619484L;
	/**
	 * 
	 */
	
	private ArrayList<Atividade> listAtividades = new ArrayList<Atividade>();
	private ArrayList<Atividade> listDesafios = new ArrayList<Atividade>();
	
	private AtividadeDAO objAtividadeDAO;
	private TarefaDAO objTarefaDAO;
	private Integer totalAtividades;
	private Integer totalDesafios;
	private Tarefa tarefa;
	private String teste;
	
	
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
			listAtividades = objAtividadeDAO.montaHistorico(tarefa, usuario, 1, 1);
			listDesafios = objAtividadeDAO.montaHistorico(tarefa, usuario, 1, 2);
			totalAtividades = listAtividades.size();
			totalDesafios = listDesafios.size();
			teste = tarefa.getNome();
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

	public ArrayList<Atividade> getListAtividades() {
		return listAtividades;
	}

	public void setListAtividades(ArrayList<Atividade> listAtividades) {
		this.listAtividades = listAtividades;
	}

	public ArrayList<Atividade> getListDesafios() {
		return listDesafios;
	}

	public void setListDesafios(ArrayList<Atividade> listDesafios) {
		this.listDesafios = listDesafios;
	}

	public Integer getTotalAtividades() {
		return totalAtividades;
	}

	public void setTotalAtividades(Integer totalAtividades) {
		this.totalAtividades = totalAtividades;
	}

	public Integer getTotalDesafios() {
		return totalDesafios;
	}

	public void setTotalDesafios(Integer totalDesafios) {
		this.totalDesafios = totalDesafios;
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

	public String getTeste() {
		return teste;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}
	
	
	
	
}
