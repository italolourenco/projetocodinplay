package model;

import java.io.Serializable;

public class Atividade implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 852138787547096354L;
	
	private Integer id_atividade;
	private String nome;
	private String descricaoProblema;
	private Integer pontuacao;
	private String respostaA;
	private String respostaB;
	private String respostaC;
	private String respostaD;
	private String respostaE;
	private Integer respostaCerta;
	private Integer tipoAtividade;
	private Tarefa objTarefa;
	private Nivel objNivel;
	
	public Integer getId_atividade() {
		return id_atividade;
	}
	public void setId_atividade(Integer id_atividade) {
		this.id_atividade = id_atividade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricaoProblema() {
		return descricaoProblema;
	}
	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}
	public Integer getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	public String getRespostaA() {
		return respostaA;
	}
	public void setRespostaA(String respostaA) {
		this.respostaA = respostaA;
	}
	public String getRespostaB() {
		return respostaB;
	}
	public void setRespostaB(String respostaB) {
		this.respostaB = respostaB;
	}
	public String getRespostaC() {
		return respostaC;
	}
	public void setRespostaC(String respostaC) {
		this.respostaC = respostaC;
	}
	public String getRespostaD() {
		return respostaD;
	}
	public void setRespostaD(String respostaD) {
		this.respostaD = respostaD;
	}
	public String getRespostaE() {
		return respostaE;
	}
	public void setRespostaE(String respostaE) {
		this.respostaE = respostaE;
	}
	public Integer getRespostaCerta() {
		return respostaCerta;
	}
	public void setRespostaCerta(Integer respostaCerta) {
		this.respostaCerta = respostaCerta;
	}
	public Integer getTipoAtividade() {
		return tipoAtividade;
	}
	public void setTipoAtividade(Integer tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}
	public Tarefa getObjTarefa() {
		return objTarefa;
	}
	public void setObjTarefa(Tarefa objTarefa) {
		this.objTarefa = objTarefa;
	}
	public Nivel getObjNivel() {
		return objNivel;
	}
	public void setObjNivel(Nivel objNivel) {
		this.objNivel = objNivel;
	}

}
