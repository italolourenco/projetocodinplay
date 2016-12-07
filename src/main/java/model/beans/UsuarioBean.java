package model.beans;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import persistence.crud.MensagemDAO;
import persistence.crud.NivelDAO;
import persistence.crud.TarefaDAO;
import persistence.crud.UsuarioDAO;
import persistence.pojo.Atividade;
import persistence.pojo.Instituicao;
import persistence.pojo.Mensagem;
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
	private ArrayList<Atividade> listHistoricoDesafio = new ArrayList<Atividade>();
	private ArrayList<Mensagem> listMensagens = new ArrayList<Mensagem>();
	
	private AtividadeDAO objAtividadeDAO;
	private TarefaDAO objTarefaDAO;
	private NivelDAO objNivel;
	private MensagemDAO objMensagemDAO;
	private Mensagem mensagem;
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
			objMensagemDAO = new MensagemDAO();
			tarefa = defineTarefa(usuario);
			mensagem = new Mensagem();
			listMensagens = objMensagemDAO.consulta();
			desafio = null;
			listAtividades = objAtividadeDAO.montaHistorico(tarefa, usuario, 1, 1);
			listDesafios = objAtividadeDAO.montaHistorico(tarefa, usuario, 1, 2);
			totalAtividades = objAtividadeDAO.contAtividadesDesafios(usuario, 1);
			totalDesafios =  objAtividadeDAO.contAtividadesDesafios(usuario, 2);
			teste = tarefa.getNome();
			createBarModels();
			listTarefas = objTarefaDAO.tarefasNivel(usuario.getObjNivel());
			listHistoricoDesafio = objAtividadeDAO.montaHistoricoDesafio(tarefa, usuario, 2);
			verificaDesafio();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String configuraBotoes(int nivel) throws Exception{
		
		Nivel nivelCp = objNivel.consulta(nivel);
		if(nivelCp != null){
			if(this.tarefa.getNivel().getId_nivel() == nivelCp.getId_nivel()){
				return "Continuar";
			}
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
	
	
	private BarChartModel initBarModel() throws Exception{
		BarChartModel model = new BarChartModel();
		
		ChartSeries usuarioGraph = new ChartSeries();
		usuarioGraph.set("Nível 1", objAtividadeDAO.contAtividades(usuario, 1));
		usuarioGraph.set("Nível 2", objAtividadeDAO.contAtividades(usuario, 2));
		usuarioGraph.set("Nível 3", objAtividadeDAO.contAtividades(usuario, 3));
		model.addSeries(usuarioGraph);
		return model;
	}
	
	private void createBarModels() throws Exception{
		createBarModel();
	}
	
	private void createBarModel() throws Exception{
		barModel = initBarModel();
		
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Níveis");
        
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção !", "Você Possui um Novo Desafio"));
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
		msgDesafio ="Não Encontrado";
		return "Bloqueado";
	}
	
	public String irTelaResolucaoDesafio(){
		if(msgDesafio.equalsIgnoreCase("Disponivel !")){
			return "telaDesafio";
			
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ainda Não !", "Você Não possui Desafios Disponiveis"));
		return "telaPrincipal";
	}
	
	public String voltaNomeStatus(Atividade atividade1) throws Exception{
		
		String status = objAtividadeDAO.RetornaStatus(usuario, atividade1);
		if(status.equals("1")){
			return "Acertou";
		}
		
		return "Errou";
	}
	
	public String enviarMensagem() throws Exception{
		
		Date date = new Date();
		mensagem.setData(transformaDate(date));
		mensagem.setObjUsuario(usuario);
		objMensagemDAO.inserir(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Obrigado!", "Sua mensagem foi enviada ao Admin"));
		return null;
	}
	
	public String transformaDate(Date data){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
		return dateFormat.format(data);
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

	public ArrayList<Atividade> getListHistoricoDesafio() {
		return listHistoricoDesafio;
	}

	public void setListHistoricoDesafio(ArrayList<Atividade> listHistoricoDesafio) {
		this.listHistoricoDesafio = listHistoricoDesafio;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public ArrayList<Mensagem> getListMensagens() {
		return listMensagens;
	}

	public void setListMensagens(ArrayList<Mensagem> listMensagens) {
		this.listMensagens = listMensagens;
	}
	
	
	
	
}
