package persistence.crud;

import java.util.Date;

import persistence.pojo.Atividade;
import persistence.pojo.Usuario;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HistoricoDAO extends DAO {
	
	public void inserir (Usuario usuario, Atividade atividade, String data, int status) throws Exception{
		
		open();
		
		stmt = con.prepareStatement("INSERT INTO usuario_atividade (id_usuario, id_atividade, data_realizada, status) VALUES (?,?,?,?);");
		
		stmt.setInt(1, usuario.getId_usuario());
		stmt.setInt(2, atividade.getId_atividade());
		stmt.setString(3, data);
		stmt.setInt(4, status);
		stmt.execute();
		close();
		
	}

}
