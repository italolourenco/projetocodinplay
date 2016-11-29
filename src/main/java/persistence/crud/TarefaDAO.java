package persistence.crud;

import java.util.ArrayList;

import persistence.pojo.Nivel;
import persistence.pojo.Tarefa;

public class TarefaDAO extends DAO {
	
	public ArrayList<Tarefa> consulta() throws Exception {
		
	    NivelDAO objNivelDAO = new NivelDAO();
		ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
		Tarefa tarefa;
		Nivel nivel;
		
		
		String sql = "SELECT * from tarefas";
		
		open();
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		while(rs.next()){
			
			tarefa = new Tarefa();
			
			tarefa.setId_tarefa(rs.getInt("id_tarefa"));
			tarefa.setNome(rs.getString("nome"));
			tarefa.setPontuacao_max(rs.getInt("pontuacao_max"));
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setNivel(objNivelDAO.consulta(rs.getInt("id_nivel")));
			tarefas.add(tarefa);
		}
		close();
		return tarefas;
		
		
	}
	
	public Tarefa consulta(int id_tarefa) throws Exception{
		
		Nivel nivel = null;
		Tarefa tarefa = null;
		NivelDAO objNivelDAO = new NivelDAO();
		
		String sql = "SELECT * from tarefa where tarefa.id_tarefa = '" + id_tarefa + "';";
		open();
		
		st = con.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()){
			
			
			tarefa = new Tarefa();
			tarefa.setId_tarefa(rs.getInt("id_tarefa"));
			tarefa.setNome(rs.getString("nome"));
			tarefa.setPontuacao_max(rs.getInt("pontuacao_max"));
			tarefa.setDescricao(rs.getString("descricao"));
			nivel = objNivelDAO.consulta(rs.getInt("id_nivel"));
			tarefa.setNivel(nivel);
		}
		close();
		return tarefa;
	}

}
