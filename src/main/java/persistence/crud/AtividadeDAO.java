package persistence.crud;

import java.sql.SQLException;

import persistence.pojo.Atividade;
import persistence.pojo.Instituicao;
import persistence.pojo.Nivel;
import persistence.pojo.Tarefa;

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

}
