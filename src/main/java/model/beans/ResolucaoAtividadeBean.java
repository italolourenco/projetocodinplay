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
import persistence.crud.TarefaDAO;
import persistence.crud.UsuarioDAO;
import persistence.pojo.Atividade;
import persistence.pojo.Nivel;
import persistence.pojo.Tarefa;
import persistence.pojo.Usuario;

@ManagedBean(name = "resolucaoAtividadeBean")
@SessionScoped
public class ResolucaoAtividadeBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8902559209257287941L;
	
	private ArrayList<Atividade> listAtividades = new ArrayList<Atividade>();
	private ArrayList<Nivel> listNiveis = new ArrayList<Nivel>();
	
	private AtividadeDAO objAtividadeDAO;
	private HistoricoDAO objHistorico;
	private TarefaDAO objTarefaDAO;
	private UsuarioDAO objUsuarioDAO;
	private NivelDAO objNivelDAO;
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
		
		ArrayList<Atividade> list = new ArrayList<Atividade>();
		 
		int x = 0;
		list = objAtividadeDAO.preparaAtividadesSemHist(tarefa);
		while(list.size() != x){
			
			if(objUsuarioDAO.verificaRegistro(usuario, list.get(x))){
				if(objAtividadeDAO.verificaAcerto(usuario, list.get(x), 1, 0)){
					listAtividades.add(list.get(x));
				}
			}
			else{
				listAtividades.add(list.get(x));
			}
			x++;
		}
		
	}
	
	public void defineAtividade() throws Exception{
		
			if(listAtividades.size() > 0){
				this.atividade = listAtividades.get(0);
				listAtividades.remove(0);
			}
			else{
				preparaAtividades();
			}
	}
	
	public String verificaResposta() throws Exception{
		
		int novaPontuacao;
		
		if(resposta == atividade.getRespostaCerta()){
			
		   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Parabéns", "Resposta Correta "));
		   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Voce Recebeu", atividade.getPontuacao() + "Pontos"));
	       status = 1;
	       //salvar a pontuacao nova do usuario;
	       novaPontuacao = usuario.getPontuacao() + atividade.getPontuacao();
	       usuario.setPontuacao(novaPontuacao);
	       objUsuarioDAO.updatePontuacao(usuario);
		   
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops ...", "Resposta Incorreta, não desista!"));
	        status = 0;
	        
		}
		
		data = new Date();
		sData = transformaDate(data);
		objHistorico.inserir(usuario, atividade, sData, status);
		
		//Verificando se o usuario vai passar para o proximo nivel
		listNiveis = objNivelDAO.consulta();
		if(usuario.getObjNivel().getPontuacaoTotal() <= usuario.getPontuacao()){
			usuario.setObjNivel(listNiveis.get(usuario.getObjNivel().getId_nivel()));
			objUsuarioDAO.updateNivel(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Parabéns", "Você foi para o " + usuario.getObjNivel().getNome()));
			//FacesContext.getCurrentInstance().getExternalContext().redirect("tela_progressaoUsuario.jsf");
			return "progressaoUsuario";
			
		}
		else{
			if(usuario.getPontuacao() >= tarefa.getPontuacao_max()){
			
				this.tarefa = defineTarefa(usuario);
				listAtividades.clear();
				preparaAtividades();
				defineAtividade();
				//Mensagem informando a conclusão de uma tarefa do nível, mas n funfa
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Parabéns", "Você foi promovido para " + tarefa.getNome()));
				return "menuNivel";
				//Nesse ponto o usuario concluiu a etapa, vai ser redirecionado para o menu do nível
				//FacesContext.getCurrentInstance().getExternalContext().redirect("tela_menuNivel.jsf");
			}
			else{
				
				preparaAtividades();
				defineAtividade();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado para", "Atividade " + atividade.getNome()));
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado para", "Atividade " + resposta));
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado para", "Atividade " + atividade.getRespostaCerta()));
				return "continuar";
				}
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

	
	
	
	

}
