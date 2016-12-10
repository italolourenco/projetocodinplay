package persistence;

import java.util.ArrayList;

import model.Instituicao;
import model.Mensagem;
import model.Usuario;

public class MensagemDAO extends DAO {
	
	
	public void inserir(Mensagem mensagem) throws Exception{
		
		open();
		stmt = con.prepareStatement("INSERT INTO mensagem (data, mensagem, id_usuario) VALUES (?,?,?);");
		stmt.setString(1, mensagem.getData());
		stmt.setString(2, mensagem.getMensagem());
		stmt.setInt(3, mensagem.getObjUsuario().getId_usuario());
		stmt.execute();
		close();
	}
	
	public ArrayList<Mensagem> consulta () throws Exception{
		
		ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
		Mensagem mensagem;
		String sql = "SELECT * from mensagem";
		
		UsuarioDAO objUsuarioDAO = new UsuarioDAO();
		Usuario usuario;
		
		open();
		
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		while(rs.next()){
			
			mensagem = new Mensagem();
			mensagem.setId_mensagem(rs.getInt("id_mensagem"));
			mensagem.setData(rs.getString("data"));
			mensagem.setMensagem(rs.getString("mensagem"));
			
			usuario = objUsuarioDAO.consulta(rs.getInt("id_usuario"));
			mensagem.setObjUsuario(usuario);
			mensagens.add(mensagem);
			
		}
		close();
		return mensagens;
	}
	
}
