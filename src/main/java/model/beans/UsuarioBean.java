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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

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
	private ArrayList<Tarefa> listTarefas = new ArrayList<Tarefa>();
	
	private AtividadeDAO objAtividadeDAO;
	private TarefaDAO objTarefaDAO;
	private NivelDAO objNivel;
	private Integer totalAtividades;
	private Integer totalDesafios;
	private Tarefa tarefa;
	private String teste;
	private String nomeBotao = "Continuar";
	private boolean status = false;
	private Atividade desafio;
	private String msgDesafio;
	private String msgBotao;
	
	private BarChartModel barModel;
	
	
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
			desafio = null;
			listAtividades = objAtividadeDAO.montaHistorico(tarefa, usuario, 1, 1);
			listDesafios = objAtividadeDAO.montaHistorico(tarefa, usuario, 1, 2);
			totalAtividades = objAtividadeDAO.contAtividadesDesafios(usuario, 1);
			totalDesafios =  objAtividadeDAO.contAtividadesDesafios(usuario, 2);
			teste = tarefa.getNome();
			createBarModels();
			listTarefas = objTarefaDAO.tarefasNivel(usuario.getObjNivel());
			verificaDesafio();
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
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ainda N�o !", "Esse n�vel esta bloqueado, continue jogando para desbloquear  !.");
	        RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}
	
	
	private BarChartModel initBarModel() throws Exception{
		BarChartModel model = new BarChartModel();
		
		ChartSeries usuarioGraph = new ChartSeries();
		usuarioGraph.set("N�vel 1", objAtividadeDAO.contAtividades(usuario, 1));
		usuarioGraph.set("N�vel 2", objAtividadeDAO.contAtividades(usuario, 2));
		usuarioGraph.set("N�vel 3", objAtividadeDAO.contAtividades(usuario, 3));
		model.addSeries(usuarioGraph);
		return model;
	}
	
	private void createBarModels() throws Exception{
		createBarModel();
	}
	
	private void createBarModel() throws Exception{
		barModel = initBarModel();
		
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("N�veis");
        
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Numero de Atividades");
        yAxis.setMin(0);
        yAxis.setMax(10);
        
        barModel.setSeriesColors("f44336");
	}
	
	public String verificaDesafio() throws Exception{
		
		ArrayList<Atividade> listDesafios = new ArrayList<Atividade>();
		
		int x;
		int y;
		for(x = 0; x < listTarefas.size(); x++){
			if(usuario.getPontuacao() >= listTarefas.get(x).getPontuacao_max()){
				listDesafios = objAtividadeDAO.preparaAtividadesSemHist(listTarefas.get(x), 2);
				y = 0;
				for(y = 0; y < listDesafios.size(); y++ ){
					if(!objAtividadeDAO.verificaHist(usuario,listDesafios.get(y))){
						desafio = listDesafios.get(y);					}
				}	
		}
		
		if(desafio != null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aten��o !", "Voc� Possui um Novo Desafio"));
			msgDesafio = "Disponivel !";
			msgBotao = "Iniciar ";
			return "Iniciar";
		}
		else{
			msgDesafio ="Indisponivel";
			msgBotao = "Bloqueado";
			return "Bloqueado";
			}
		}
		msgDesafio ="N�o Encontrado";
		return "Bloqueado";
	}
	

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
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

	public void sair() throws IOException{
		
		FacesContext facesContext = FacesContext.getCurrentInstance();  
		HttpSession session = (HttpSession) facesContext .getExternalContext().getSession(false);  
		session.invalidate(); 
		FacesContext.getCurrentInstance().getExternalContext().redirect("erroIndex.jsf");
	}

	public ArrayList<Tarefa> getListTarefas() {
		return listTarefas;
	}

	public void setListTarefas(ArrayList<Tarefa> listTarefas) {
		this.listTarefas = listTarefas;
	}

	public Atividade getDesafio() {
		return desafio;
	}

	public void setDesafio(Atividade desafio) {
		this.desafio = desafio;
	}

	public String getMsgDesafio() {
		return msgDesafio;
	}

	public void setMsgDesafio(String msgDesafio) {
		this.msgDesafio = msgDesafio;
	}

	public String getMsgBotao() {
		return msgBotao;
	}

	public void setMsgBotao(String msgBotao) {
		this.msgBotao = msgBotao;
	}

	public NivelDAO getObjNivel() {
		return objNivel;
	}

	public void setObjNivel(NivelDAO objNivel) {
		this.objNivel = objNivel;
	}
	
	
	
}
