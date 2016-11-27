package persistence.pojo;

public class Instituicao {
	
	private Integer id_instituicao;
	private String nome;
	private String abreviacao;
	private String estado;
	private String telefone;
	private String site;
	
	
	public Integer getId_instituicao() {
		return id_instituicao;
	}
	public void setId_instituicao(Integer id_instituicao) {
		this.id_instituicao = id_instituicao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAbreviacao() {
		return abreviacao;
	}
	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}

}
