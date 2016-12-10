package model;

import java.io.Serializable;

public class Nivel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7042923987599619282L;
	
	private Integer id_nivel;
	private String nome;
	private Integer pontuacaoTotal;
	private String descricaoTema;
	
	public Integer getId_nivel() {
		return id_nivel;
	}
	public void setId_nivel(Integer id_nivel) {
		this.id_nivel = id_nivel;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPontuacaoTotal() {
		return pontuacaoTotal;
	}
	public void setPontuacaoTotal(Integer pontuacaoTotal) {
		this.pontuacaoTotal = pontuacaoTotal;
	}
	public String getDescricaoTema() {
		return descricaoTema;
	}
	public void setDescricaoTema(String descricaoTema) {
		this.descricaoTema = descricaoTema;
	}
	
	

}
