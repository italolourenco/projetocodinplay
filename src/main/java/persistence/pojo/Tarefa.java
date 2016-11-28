package persistence.pojo;

import java.io.Serializable;

public class Tarefa  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4185941214039254370L;
	private Integer id_tarefa;
	private String nome;
	private Integer pontuacao_max;
	private String descricao;
	private Nivel nivel;
	public Integer getId_tarefa() {
		return id_tarefa;
	}
	public void setId_tarefa(Integer id_tarefa) {
		this.id_tarefa = id_tarefa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPontuacao_max() {
		return pontuacao_max;
	}
	public void setPontuacao_max(Integer pontuacao_max) {
		this.pontuacao_max = pontuacao_max;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Nivel getNivel() {
		return nivel;
	}
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	
	
	

}
