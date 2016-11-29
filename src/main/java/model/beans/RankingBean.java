package model.beans;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import persistence.crud.InstituicaoDAO;
import persistence.crud.NivelDAO;
import persistence.crud.PatenteDAO;
import persistence.crud.RankingDAO;
import persistence.crud.UsuarioDAO;
import persistence.pojo.Instituicao;
import persistence.pojo.Nivel;
import persistence.pojo.Patente;
import persistence.pojo.Usuario;

@ManagedBean(name = "rankingBean")
@ViewScoped
public class RankingBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4197663574192718633L;
	
	private RankingDAO objRankingDAO = new RankingDAO();
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	
	 private ArrayList<Usuario> filteredUsuarios;
	
	public RankingDAO getObjRankingDAO() {
		return objRankingDAO;
	}

	public void setObjRankingDAO(RankingDAO objRankingDAO) {
		this.objRankingDAO = objRankingDAO;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@PostConstruct
	public void init(){
		
		try {
			usuarios = objRankingDAO.consultaRanking();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<Usuario> getFilteredUsuarios() {
		return filteredUsuarios;
	}

	public void setFilteredUsuarios(ArrayList<Usuario> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}
	
	
}
