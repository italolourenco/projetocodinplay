package model.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import persistence.crud.AtividadeDAO;

@ManagedBean(name = "resolucaoAtividadeBean")
@ViewScoped
public class ResolucaoAtividadeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8902559209257287941L;
	
	AtividadeDAO objAtividadeDAO = new AtividadeDAO();
	
	
	
	

}
