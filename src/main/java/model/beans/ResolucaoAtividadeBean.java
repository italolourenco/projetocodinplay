package model.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import java.util.Date;
import persistence.crud.AtividadeDAO;
import persistence.crud.HistoricoDAO;
import persistence.crud.TarefaDAO;
import persistence.crud.UsuarioDAO;
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
	private HistoricoDAO objHistorico;
	private TarefaDAO objTarefaDAO;
	private UsuarioDAO objUsuarioDAO;
	private Tarefa tarefa;
	private Tarefa tarefa2;
	private Atividade atividade;
	private int resposta;
	private Date data;
	private String sData;
	private Integer status;
	
	
    @ManagedProperty(value = "#{usuario}")
    private Usuario usuario; 
    
	@PostConstruct
	public void init(){
		
		try {
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			usuario = (Usuario)session.getAttribute("identificaUsuario"); 
			
			objHistorico = new HistoricoDAO();
			objAtividadeDAO = new AtividadeDAO();
			objTarefaDAO = new TarefaDAO();
			objUsuarioDAO = new UsuarioDAO();
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
			tarefa2 = tarefa;
		}
	}
	
	public void defineAtividade(){
		
		this.atividade = listAtividades.get(0);
		listAtividades.remove(0);
	}
	
	public void verificaResposta() throws Exception{
		
		//ArrayList<Atividade> listHist = new ArrayList<Atividade>();
		//ArrayList<Atividade> listAtiv = new ArrayList<Atividade>();
		int novaPontuacao;
		
		if(this.resposta == atividade.getRespostaCerta()){
			
	       FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Parabéns !", "Resposta Correta ! Você ganhou " + atividade.getPontuacao() + " Pontos");
	       RequestContext.getCurrentInstance().showMessageInDialog(message);
	       status = 1;
	       //salvar a pontuacao nova do usuario;
	       novaPontuacao = usuario.getPontuacao() + atividade.getPontuacao();
	       usuario.setPontuacao(novaPontuacao);
	       objUsuarioDAO.updatePontuacao(usuario);
		   
		}
		else {
			
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops ... !", "Resposta Incorreta! Não desista :)");
	        RequestContext.getCurrentInstance().showMessageInDialog(message);
	        status = 0;
	        
		}
		
		data = new Date();
		sData = transformaDate(data);
		objHistorico.inserir(usuario, atividade, sData, status);
		
		if(usuario.getPontuacao() >= tarefa.getPontuacao_max()){
			
			tarefa = defineTarefa(usuario);
		    preparaAtividades();
			defineAtividade();
			//Mensagem informando a conclusão de uma tarefa do nível, mas n funfa
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Parabéns !", "Você concluiu essa Etapa !");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			//Nesse ponto o usuario concluiu a etapa, vai ser redirecionado para o menu do nível
			FacesContext.getCurrentInstance().getExternalContext().redirect("tela_menuNivel.jsf");
		}
		else{
			//logica pra puxar uma nova atividade.
			if(listAtividades.size() == 0){
				//Caso o usuário não possuia mais tarefas novas em uma tarefa
				listAtividades = objAtividadeDAO.montaHistorico(tarefa, usuario, 0, 1);
			}
			defineAtividade();
		}
}
			
	
	public String transformaDate(Date data){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
		return dateFormat.format(data);
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Tarefa getTarefa2() {
		return tarefa2;
	}

	public void setTarefa2(Tarefa tarefa2) {
		this.tarefa2 = tarefa2;
	}
	
	
	
	

}
