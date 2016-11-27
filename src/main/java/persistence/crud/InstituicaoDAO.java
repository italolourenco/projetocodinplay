package persistence.crud;
import persistence.pojo.Instituicao;


import java.util.ArrayList;




public class InstituicaoDAO extends DAO {
	
	public ArrayList<Instituicao> consulta() throws Exception {
		
		ArrayList<Instituicao> instituicoes = new ArrayList<Instituicao>();
		Instituicao instituicao;
		String sql = "SELECT * from instituicao";
		
		open();
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		while(rs.next()){
			
			instituicao = new Instituicao();
			
			instituicao.setId_instituicao(rs.getInt("id_instituicao"));
			instituicao.setNome(rs.getString("nome"));
			instituicao.setAbreviacao(rs.getString("abreviacao"));
			instituicao.setEstado(rs.getString("estado"));
			instituicao.setTelefone(rs.getString("telefone"));
			instituicao.setSite(rs.getString("site"));
			instituicoes.add(instituicao);
		}
		close();
		return instituicoes;
		
		
	}

}
