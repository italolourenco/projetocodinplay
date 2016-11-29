package persistence.crud;

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

}
