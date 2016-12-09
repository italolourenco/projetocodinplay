package model.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import java.util.Date;
import persistence.crud.AtividadeDAO;
import persistence.crud.HistoricoDAO;
import persistence.crud.NivelDAO;
import persistence.crud.PatenteDAO;
import persistence.crud.TarefaDAO;
import persistence.crud.UsuarioDAO;
import persistence.pojo.Atividade;
import persistence.pojo.Nivel;
import persistence.pojo.Tarefa;
import persistence.pojo.Usuario;

@ManagedBean(name = "resolucaoDesafioBean")
@RequestScoped
public class ResolucaoDesafioBean implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6784380071521881163L;
	
	private ArrayList<Atividade> listAtividades = new ArrayList<Atividade>();
	private ArrayList<Nivel> listNiveis = new ArrayList<Nivel>();
	private ArrayList<Tarefa> listTarefas = new ArrayList<Tarefa>();
	
	private AtividadeDAO objAtividadeDAO;
	private HistoricoDAO objHistorico;
	private TarefaDAO objTarefaDAO;
	private UsuarioDAO objUsuarioDAO;
	private NivelDAO objNivelDAO;
	private PatenteDAO objPatenteDAO;
	
	private Tarefa tarefa;
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
			
			resposta = 0;
			objHistorico = new HistoricoDAO();
			objAtividadeDAO = new AtividadeDAO();
			objTarefaDAO = new TarefaDAO();
			objUsuarioDAO = new UsuarioDAO();
			objNivelDAO = new NivelDAO();
			objPatenteDAO = new PatenteDAO();
			tarefa = defineTarefa(usuario);
			atividade = null;
			listTarefas = objTarefaDAO.tarefasNivel(usuario.getObjNivel());
			verificaDesafio();

			
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
	
	public void verificaDesafio() throws Exception{
		
		ArrayList<Atividade> listDesafios = new ArrayList<Atividade>();
		
		int x;
		int y;
		for(x = 0; x < listTarefas.size(); x++){
			if(usuario.getPontuacao() >= listTarefas.get(x).getPontuacao_max()){
				listDesafios = objAtividadeDAO.preparaAtividadesSemHist(listTarefas.get(x), 2);
				y = 0;
				for(y = 0; y < listDesafios.size(); y++ ){
					if(!objAtividadeDAO.verificaHist(usuario,listDesafios.get(y))){
						atividade = listDesafios.get(y);					
						}
				}
			}
		}
	}
	
	
	public String verificaResposta() throws Exception{
		
		int novaPontuacao;
		
		if(resposta == atividade.getRespostaCerta()){
			
		   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Resposta Correta "));
		   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Somou mais", atividade.getPontuacao() + " Pontos"));
	       status = 1;
	       //salvar a pontuacao nova do usuario;
	       novaPontuacao = usuario.getPontuacao() + atividade.getPontuacao();
	       usuario.setPontuacao(novaPontuacao);
	       objUsuarioDAO.updatePontuacao(usuario);
	       
	       if(usuario.getPontuacao() >= usuario.getObjPatente().getPontuacao_max()){
	    	   usuario.setObjPatente(objPatenteDAO.consultaPatente(usuario));
	    	   objUsuarioDAO.updatePatente(usuario);
	    	   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Patente Evoluida "));
	    	   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nova Patente", usuario.getObjPatente().getNome() + ""));
	       }
		   
		}
		else {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops ...", "Resposta Incorreta"));
	        status = 0;
	        
	        listAtividades.add(atividade);
	        
		}
		
		data = new Date();
		sData = transformaDate(data);
		objHistorico.inserir(usuario, atividade, sData, status);
		atividade = new Atividade();
		
		//Verificando se o usuario vai passar para o proximo nivel
		listNiveis = objNivelDAO.consulta();
		if(usuario.getObjNivel().getPontuacaoTotal() <= usuario.getPontuacao()){
			if(usuario.getObjNivel().getId_nivel() < 3){
				usuario.setObjNivel(listNiveis.get(usuario.getObjNivel().getId_nivel() + 1));
				objUsuarioDAO.updateNivel(usuario);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Nivel Evoluido "));
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Novo Nivel", usuario.getObjNivel().getNome() + ""));
			}
			
		}
		else{
			if(usuario.getPontuacao() >= tarefa.getPontuacao_max()){
			
				//Mensagem informando a conclusão de uma tarefa do nível, mas n funfa
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Tarefa Desbloqueada: " + tarefa.getNome()));
				//Nesse ponto o usuario concluiu a etapa, vai ser redirecionado para o menu do nível
				//FacesContext.getCurrentInstance().getExternalContext().redirect("tela_menuNivel.jsf");
			}
		}
		return "telaPrincipal";
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

	
	
	
	

}
