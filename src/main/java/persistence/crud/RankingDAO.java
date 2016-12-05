package persistence.crud;

import java.util.ArrayList;


import persistence.pojo.Usuario;

public class RankingDAO extends DAO{
	public ArrayList<Usuario> consultaRanking() throws Exception {
		
	    PatenteDAO objPatenteDAO = new PatenteDAO();
	    InstituicaoDAO objInstituicaoDAO = new InstituicaoDAO();
	    NivelDAO objNivelDAO = new NivelDAO();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario;
	
		String sql = "SELECT * from usuario WHERE usuario.tipo = 1 ORDER BY usuario.pontuacao DESC LIMIT 5;";
		
		open();
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		while(rs.next()){
			usuario = new Usuario();
			usuario.setNome(rs.getString("nome"));
			usuario.setObjPatente(objPatenteDAO.consultaPatente(rs.getInt("id_patente")));
			usuario.setObjNivel(objNivelDAO.consulta(rs.getInt("id_nivel")));
			usuario.setPontuacao(rs.getInt("pontuacao"));
			usuario.setObjInstituicao(objInstituicaoDAO.consulta(rs.getInt("id_instituicao")));
			usuarios.add(usuario);
		}
		close();
		return usuarios;
		
		
	}
}
