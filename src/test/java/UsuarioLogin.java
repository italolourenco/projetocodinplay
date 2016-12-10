

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.beans.LoginBean;
import persistence.pojo.Usuario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UsuarioLogin {
	
	private Usuario usuario = new Usuario();
	private LoginBean loginBean = new LoginBean();
	
	@Given("^Usuario entra com \"([^\"]*)\" e senha \"([^\"]*)\" validos$")
	public void usuarioEntraComESenhaValidos(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		loginBean.setEmail(arg1);
		loginBean.setSenha(arg2);
	}

	@When("^Usuario clica na opcao entrar$")
	public void usuarioClicaNaOpOEntrar() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    assertThat(loginBean.entrar(), is("entrar"));
	}

	@Then("^Usuario deve estar logado no sistema$")
	public void usuarioDeveEstarLogadoNoSistema() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		usuario = loginBean.getUsuario();
		assertThat(usuario.getNome(), is("Italo Lourenco"));
	}

}
