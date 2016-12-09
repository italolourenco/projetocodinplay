package model.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

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
import java.util.Random;

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

@ManagedBean(name = "resolucaoAtividadeBean")
@RequestScoped
public class ResolucaoAtividadeBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8902559209257287941L;
	
	private ArrayList<Atividade> listAtividades = new ArrayList<Atividade>();
	private ArrayList<Nivel> listNiveis = new ArrayList<Nivel>();
	private ArrayList<Atividade> listAtvFazer = new ArrayList<Atividade>();
	
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
			
			//Pegando a sessão atual do usuário
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
			listAtividades = objAtividadeDAO.preparaAtividadesSemHist(tarefa, 1);
			preparaAtividades();
			//defineAtividade();
			
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
		
		//ArrayList<Atividade> list = new ArrayList<Atividade>();
		 
		int x;
		//list = objAtividadeDAO.montaHistorico(tarefa, usuario, 1, 0);
		this.atividade = null;
		x = 0;
		//Verificando se existe registro para essa atividade
		while(listAtividades.size() != x && atividade == null){
			
			if(objUsuarioDAO.verificaRegistro(usuario, listAtividades.get(x))){
				if(!objAtividadeDAO.verificaAcerto(usuario, listAtividades.get(x), 1, 1)){
					this.atividade = listAtividades.get(x);
				}
			}
			else{
				this.atividade = listAtividades.get(x);
			}
		x++;
	}
	
}
	public void defineAtividade() throws Exception{
		
			atividade = new Atividade();
			atividade = listAtvFazer.get(0);
			listAtvFazer.remove(0);
	}
	
	public String verificaResposta() throws Exception{
		
		int novaPontuacao;
		if(resposta == atividade.getRespostaCerta()){
		
		   
		   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !", "Resposta Correta "));
		   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aumentando ", atividade.getPontuacao() + " Pontos"));
	       status = 1;
	       //salvar a pontuacao nova do usuario;
	       novaPontuacao = usuario.getPontuacao() + atividade.getPontuacao();
	       usuario.setPontuacao(novaPontuacao);
	       objUsuarioDAO.updatePontuacao(usuario);
	       
	       if(usuario.getPontuacao() >= usuario.getObjPatente().getPontuacao_max()){
	    	   
	    	   usuario.setObjPatente(objPatenteDAO.consultaPatente(usuario));
	    	   objUsuarioDAO.updatePatente(usuario);
	    	   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Nova Patente "));
	    	   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Patante", "" + usuario.getObjPatente().getNome()));
	       }
		   
	      
		}
		else {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops ...", "Resposta Incorreta"));
	        status = 0;
	        
		}
		
		data = new Date();
		sData = transformaDate(data);
		objHistorico.inserir(usuario, atividade, sData, status);
		resposta = 0;
		
		//Verificando se o usuario vai passar para o proximo nivel
		listNiveis = objNivelDAO.consulta();
		if(usuario.getObjNivel().getPontuacaoTotal() <= usuario.getPontuacao()){
			if(usuario.getObjNivel().getId_nivel() < 3){
				
				usuario.setObjNivel(listNiveis.get(usuario.getObjNivel().getId_nivel()));
				objUsuarioDAO.updateNivel(usuario);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Nova Nivel "));
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agora o seu nivel e ", usuario.getObjNivel().getNome() + ""));
				return "progressaoUsuario";
				
			}
			return "telaPrincipal";
			
		}
		else{
			if(usuario.getPontuacao() >= tarefa.getPontuacao_max()){
			
				//this.tarefa = defineTarefa(usuario);
				//preparaAtividades();
				//defineAtividade();
				//Mensagem informando a conclusão de uma tarefa do nível, mas n funfa
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Promovido de tarefa " + ""));
				return "menuNivel";
				//Nesse ponto o usuario concluiu a etapa, vai ser redirecionado para o menu do nível
				//FacesContext.getCurrentInstance().getExternalContext().redirect("tela_menuNivel.jsf");
			}
			else{
				
			
				preparaAtividades();
				//defineAtividade();
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado para", "Atividade " + atividade.getNome()));
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
