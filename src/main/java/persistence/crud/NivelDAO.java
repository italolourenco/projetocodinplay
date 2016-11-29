package persistence.crud;

import java.util.ArrayList;

import persistence.pojo.Instituicao;
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
			
			nivel.setId_nivel(rs.getInt("id_nivel"));
			nivel.setNome(rs.getString("nome"));
			nivel.setPontuacaoTotal(rs.getInt("pontuacaototal"));
			nivel.setDescricaoTema(rs.getString("descricaotema"));
			niveis.add(nivel);
		}
		close();
		return niveis;
		
		
	}
	
	public Nivel consulta(int id_nivel) throws Exception{
		
		Nivel nivel = null;
		String sql = "SELECT * from nivel where nivel.id_nivel = '" + id_nivel + "';";
		open();
		
		st = con.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()){
			
			nivel = new Nivel();
			
			nivel.setId_nivel(rs.getInt("id_nivel"));
			nivel.setNome(rs.getString("nome"));
			nivel.setPontuacaoTotal(rs.getInt("pontuacaototal"));
			nivel.setDescricaoTema(rs.getString("descricaotema"));
		}
		close();
		return nivel;
	}

}
