package persistence.crud;

import java.util.ArrayList;

import persistence.pojo.Nivel;

public class NivelDAO extends DAO{
	
	public ArrayList<Nivel> consulta() throws Exception {
		
		ArrayList<Nivel> niveis = new ArrayList<Nivel>();
		Nivel nivel;
		String sql = "SELECT * from nivel";
		
		open();
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		while(rs.next()){
			
			nivel = new Nivel();
			
			nivel.setId_nivel(rs.getInt("id_instituicao"));
			nivel.setNome(rs.getString("nome"));
			nivel.setPontuacaoTotal(rs.getInt("pontuacaototal"));
			nivel.setDescricaoTema(rs.getString("descricaotema"));
			niveis.add(nivel);
		}
		close();
		return niveis;
		
		
	}

}
