package persistence;
import model.Patente;
import model.Usuario;

public class PatenteDAO extends DAO{
	public Patente consultaPatente(int id) throws Exception {
		Patente patente = null;
		String sql = "SELECT * from patente where patente.id_patente = " + id + ";";
		open();
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		while(rs.next()){
			patente = new Patente();
			patente.setId_patente(rs.getInt("id_patente"));
			patente.setNome(rs.getString("nome"));
			patente.setPontuacao_max(rs.getInt("pontuacao_max"));
			patente.setPontuacao_min(rs.getInt("pontuacao_min"));
		}
		close();
		return patente;
	}
	
	public Patente consultaPatente(Usuario usuario) throws Exception{
		
		Patente patente = null;
		String sql = "SELECT * from patente where patente.pontuacao_max >= " + usuario.getPontuacao() + "and patente.pontuacao_min <= " +usuario.getPontuacao() +";";
		open();
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		while(rs.next()){
			patente = new Patente();
			patente.setId_patente(rs.getInt("id_patente"));
			patente.setNome(rs.getString("nome"));
			patente.setPontuacao_max(rs.getInt("pontuacao_max"));
			patente.setPontuacao_min(rs.getInt("pontuacao_min"));
		}
		close();
		return patente;
		
	}
}
