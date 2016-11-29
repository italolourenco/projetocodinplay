package persistence.pojo;

import java.io.Serializable;

public class Patente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5978685280814947728L;
	private Integer id_patente;
	private String nome;
	private Integer pontuacao_min;
	private Integer pontuacao_max;
	
	
	public Integer getId_patente() {
		return id_patente;
	}
	public void setId_patente(Integer id_patente) {
		this.id_patente = id_patente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPontuacao_min() {
		return pontuacao_min;
	}
	public void setPontuacao_min(Integer pontuacao_min) {
		this.pontuacao_min = pontuacao_min;
	}
	public Integer getPontuacao_max() {
		return pontuacao_max;
	}
	public void setPontuacao_max(Integer pontuacao_max) {
		this.pontuacao_max = pontuacao_max;
	}
	
	

}
