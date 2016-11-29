package persistence.crud;

import persistence.pojo.Instituicao;
import persistence.pojo.Usuario;


public class UsuarioDAO extends DAO {
	
	public void inserirUsuario(Usuario usuario) throws Exception{		
			open();
			stmt = con.prepareStatement("INSERT INTO usuario(nome, email,datanascimento,telefone,tipo,sexo,senha,pontuacao,id_instituicao,id_patente,id_nivel)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?);");
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getDataNascimento());
			stmt.setString(4, usuario.getTelefone());
			stmt.setInt(5, usuario.getTipo());
			stmt.setInt(6,usuario.getSexo());
			stmt.setString(7, usuario.getSenha());
			stmt.setInt(8,usuario.getPontuacao());
			stmt.setInt(9, usuario.getObjInstituicao().getId_instituicao());
			stmt.setInt(10, usuario.getObjPatente().getId_patente());
			stmt.setInt(11, usuario.getObjNivel().getId_nivel());
			stmt.execute();
	}

	public boolean consultaEmail(String email) throws Exception {
		String sql = "SELECT * from usuario where usuario.email = '" + email + "';";
		open();
		st = con.createStatement();
		rs = st.executeQuery(sql);
		boolean hasResult = rs.next();
		close();
		return hasResult;
	}
}
