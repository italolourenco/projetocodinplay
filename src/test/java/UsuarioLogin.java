

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Usuario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import controller.LoginBean;

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

	
	//@Then("^Deve ser exibida uma mensagem de erro para o usu?rio$")
	//public void deveSerExibidaUmaMensagemDeErroParaOUsuRio() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	//    throw new PendingException();
	//}

}
