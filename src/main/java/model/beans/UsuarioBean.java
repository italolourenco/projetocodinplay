package model.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import persistence.crud.AtividadeDAO;
import persistence.crud.InstituicaoDAO;
import persistence.crud.NivelDAO;
import persistence.crud.TarefaDAO;
import persistence.crud.UsuarioDAO;
import persistence.pojo.Atividade;
import persistence.pojo.Instituicao;
import persistence.pojo.Nivel;
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
	private NivelDAO objNivel;
	private Integer totalAtividades;
	private Integer totalDesafios;
	private Tarefa tarefa;
	private String teste;
	private String nomeBotao = "Continuar";
	private boolean status = false;
	
	
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
			listDesafios = objAtividadeDAO.montaHistorico(tarefa, usuario, 1, 2);
			totalAtividades = listAtividades.size();
			totalDesafios = listDesafios.size();
			teste = tarefa.getNome();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String configuraBotoes(int nivel) throws Exception{
		
		Nivel nivelCp = objNivel.consulta(nivel);
		if(this.tarefa.getNivel().getId_nivel() == nivelCp.getId_nivel()){
			return "Continuar";
		}
		return "Bloqueado";
	}
	
	public Tarefa defineTarefa(Usuario usuario) throws Exception{
		
		tarefa = new Tarefa();
		tarefa = objTarefaDAO.encontraTarefa(usuario);
		return tarefa;
	}
	
	public String irTelaMenuNivel(int nivel) throws Exception{
		
		String result = configuraBotoes(nivel);
		if(result.equalsIgnoreCase("Continuar")){
			
			return "menunivel";
		}
		return "bloqueado";
	}
	
	public String configurarBotoesNivel(String nome){
		if(nome.equalsIgnoreCase(tarefa.getNome())){
			return "Continuar";
		}
		return "Bloqueado";
	}
	
	public void irTelaResolucao(String nome) throws IOException{
		
		String result = configurarBotoesNivel(nome);
		if(result.equalsIgnoreCase("Continuar")){
			FacesContext.getCurrentInstance().getExternalContext().redirect("tela_resolucaoAtividade.jsf");
		}
		else{
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ainda Não !", "Esse nível esta bloqueado, continue jogando para desbloquear  !.");
	        RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
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

	public String getNomeBotao() {
		return nomeBotao;
	}

	public void setNomeBotao(String nomeButao) {
		this.nomeBotao = nomeButao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
}
