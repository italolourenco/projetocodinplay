package persistence.crud;

import java.sql.SQLException;
import java.util.ArrayList;

import persistence.pojo.Atividade;
import persistence.pojo.Instituicao;
import persistence.pojo.Nivel;
import persistence.pojo.Tarefa;
import persistence.pojo.Usuario;

public class AtividadeDAO extends DAO {
	
	public boolean consulta (Atividade atividade) throws Exception{
		
		String sql = "SELECT * from atividade where atividade.nome = '" + atividade.getNome() + "' and atividade.id_tarefa = " + atividade.getObjTarefa().getId_tarefa() + ";";
		open();
		st = con.createStatement();
		rs = st.executeQuery(sql);
		boolean hasResult = rs.next();
		close();		
		return hasResult;
	}
	
	public void inserir (Atividade atividade) throws Exception {
		
		open();
		
		stmt = con.prepareStatement("INSERT INTO atividade (nome, descricaoproblema, pontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
		
		stmt.setString(1, atividade.getNome());
		stmt.setString(2, atividade.getDescricaoProblema());
		stmt.setInt(3, atividade.getPontuacao());
		stmt.setString(4, atividade.getRespostaA());
		stmt.setString(5, atividade.getRespostaB());
		stmt.setString(6, atividade.getRespostaC());
		stmt.setString(7, atividade.getRespostaD());
		stmt.setString(8, atividade.getRespostaE());
		stmt.setInt(9, atividade.getRespostaCerta());
		stmt.setInt(10, atividade.getTipoAtividade());
		stmt.setInt(11, atividade.getObjTarefa().getId_tarefa());
		stmt.setInt(12, atividade.getObjNivel().getId_nivel());
		stmt.execute();
		close();
	}
	
	public Atividade encontraAtividades(Usuario usuario) throws Exception {
		
		Atividade atividade = null;
		String sql = "SELECT * from atividade "
					+ "INNER JOIN usuario_atividade ON atividade.id_atividade = usuario_atividade.id_atividade"
					+ "WHERE usuario_atividade.id_usuario = id_usuario and usuario_atividade.status = 0;" ;
		open();
		st = con.createStatement();
		rs = st.executeQuery(sql);
		boolean hasResult = rs.next();
		
		return atividade;
	}
	
	public ArrayList<Atividade> montaHistorico(Tarefa tarefa, Usuario usuario, int tipo, int status) throws Exception{
		
		Atividade atividade = null;
		ArrayList<Atividade> listAtividades = new ArrayList<Atividade>();
		String sql = "SELECT * from atividade "
				   + "INNER JOIN usuario_atividade ON atividade.id_atividade = usuario_atividade.id_atividade "
				   + "WHERE usuario_atividade.id_usuario = " + usuario.getId_usuario() + "and usuario_atividade.status = " + status + " and atividade.tipo = " + tipo + ";";
		open();
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		while(rs.next()){
			
			atividade = new Atividade();
			
			atividade.setId_atividade(rs.getInt("id_atividade"));
			atividade.setNome(rs.getString("nome"));
			atividade.setDescricaoProblema("descricaoproblema");
			atividade.setPontuacao(rs.getInt("pontuacao"));
			atividade.setRespostaA(rs.getString("respostaa"));
			atividade.setRespostaB(rs.getString("respostab"));
			atividade.setRespostaC(rs.getString("respostac"));
			atividade.setRespostaD(rs.getString("respostad"));
			atividade.setRespostaE(rs.getString("respostae"));
			atividade.setRespostaCerta(rs.getInt("respostaCerta"));
			atividade.setObjTarefa(tarefa);
			atividade.setObjNivel(tarefa.getNivel());
			
			listAtividades.add(atividade);
		}
		
		
		return listAtividades;
	}
	

}
