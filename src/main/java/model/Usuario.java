package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1701238245897178967L;
	
	private Integer id_usuario;
	private String nome;
	private String email;
	private String dataNascimento;
	private String telefone;
	private Integer tipo;
	private Integer sexo;
	private String senha;
	private Integer pontuacao;
	private Patente objPatente;
	private Instituicao objInstituicao;
	private Nivel objNivel;
	private ArrayList<Atividade> listAtividades;
	
	
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public Integer getSexo() {
		return sexo;
	}
	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Integer getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	public Patente getObjPatente() {
		return objPatente;
	}
	public void setObjPatente(Patente objPatente) {
		this.objPatente = objPatente;
	}
	public Instituicao getObjInstituicao() {
		return objInstituicao;
	}
	public void setObjInstituicao(Instituicao objInstituicao) {
		this.objInstituicao = objInstituicao;
	}
	public Nivel getObjNivel() {
		return objNivel;
	}
	public void setObjNivel(Nivel objNive) {
		this.objNivel = objNive;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ArrayList<Atividade> getListAtividades() {
		return listAtividades;
	}
	public void setListAtividades(ArrayList<Atividade> listAtividades) {
		this.listAtividades = listAtividades;
	}
	
	
	
}
