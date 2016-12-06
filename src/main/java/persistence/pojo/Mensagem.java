package persistence.pojo;

public class Mensagem {
	
	private Integer id_mensagem;
	private String data;
	private Usuario objUsuario;
	
	
	public Integer getId_mensagem() {
		return id_mensagem;
	}
	public void setId_mensagem(Integer id_mensagem) {
		this.id_mensagem = id_mensagem;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Usuario getObjUsuario() {
		return objUsuario;
	}
	public void setObjUsuario(Usuario objUsuario) {
		this.objUsuario = objUsuario;
	}
	
	
	

}
