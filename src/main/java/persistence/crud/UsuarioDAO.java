package persistence.crud;

import persistence.pojo.Atividade;
import persistence.pojo.Instituicao;
import persistence.pojo.Nivel;
import persistence.pojo.Patente;
import persistence.pojo.Tarefa;
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
	
	public Usuario consulta (String email) throws Exception{
		
		Usuario usuario = null;
		Patente patente = null;
		Instituicao instituicao = null;
		Nivel nivel = null;
		
		PatenteDAO objPatenteDAO = new PatenteDAO();
		InstituicaoDAO objInstituicaoDAO = new InstituicaoDAO();
		NivelDAO objNivelDAO = new NivelDAO();
		
		String sql = "SELECT * from usuario where usuario.email = '" + email + "';";
		open();
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		while(rs.next()){
			
			usuario = new Usuario();
			
			usuario.setId_usuario(rs.getInt("id_usuario"));
			usuario.setNome(rs.getString("nome"));
			usuario.setTelefone(rs.getString("email"));
			usuario.setDataNascimento(rs.getString("datanascimento"));
			usuario.setTelefone(rs.getString("telefone"));
			usuario.setTipo(rs.getInt("tipo"));
			usuario.setSexo(rs.getInt("sexo"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setPontuacao(rs.getInt("pontuacao"));
			usuario.setObjInstituicao(objInstituicaoDAO.consulta(rs.getInt("id_instituicao")));
			usuario.setObjNivel(objNivelDAO.consulta(rs.getInt("id_nivel")));
			usuario.setObjPatente(objPatenteDAO.consultaPatente(rs.getInt("id_patente")));
			
		}
		close();
		return usuario;
	}
	
	public void updatePontuacao(Usuario usuario) throws Exception{
		
		open();
		stmt = con.prepareStatement("UPDATE usuario SET pontuacao = " + usuario.getPontuacao() + " WHERE usuario.id_usuario = " + usuario.getId_usuario() + ";");
		stmt.execute();
	
	}
	
	public void updateNivel(Usuario usuario) throws Exception{
		
		open();
		stmt = con.prepareStatement("UPDATE usuario SET id_nivel = " + usuario.getObjNivel().getId_nivel() + " WHERE usuario.id_usuario = " + usuario.getId_usuario() + ";");
		stmt.execute();
	
	}
	
	public void updateUsuario(Usuario usuario) throws Exception{
		
		open();
		stmt = con.prepareStatement("UPDATE usuario SET nome = '"+usuario.getNome()+"',email='"+usuario.getEmail()+"',datanascimento='"+usuario.getDataNascimento()+"',telefone='"+usuario.getTelefone()+"',sexo="+usuario.getSexo()+",senha='"+usuario.getSenha()+"',id_instituicao="+usuario.getObjInstituicao().getId_instituicao()+"WHERE usuario.id_usuario = " + usuario.getId_usuario() + ";");
		stmt.execute();
	}
	
	public boolean verificaRegistro(Usuario usuario, Atividade atividade) throws Exception {
		
		String sql = "SELECT * from usuario INNER JOIN usuario_atividade ON usuario_atividade.id_usuario = usuario.id_usuario WHERE usuario_atividade.id_usuario =" + usuario.getId_usuario() +  " and usuario_atividade.id_atividade = " +atividade.getId_atividade() +";" ;
		open();
		st = con.createStatement();
		rs = st.executeQuery(sql);
		boolean hasResult = rs.next();
		close();
		return hasResult;
	}
}
