package br.com.cyber.conquista.frameworkConquista.execute.steps;

import static org.junit.Assert.assertEquals;

import br.com.cyber.conquista.frameworkConquista.pages.BloqueioPage;
import br.com.cyber.conquista.frameworkConquista.pages.CadastroPages;
import br.com.cyber.conquista.frameworkConquista.pages.EmailPages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CadastroSteps {

	private CadastroPages cadastroPages = new CadastroPages();
	private EmailPages emailPages = new EmailPages();
	private String token;
	private BloqueioPage bloqueio = new BloqueioPage();

	@Given("que um usuário acessou a opção de cadastro")
	public void queUmUsuárioAcessouAOpçãoDeCadastro() {
		cadastroPages.AcessandoAPaginaDeCadastro();
	}
	
	@Given("acesso a URL de bloqueio de nova conta com token expirado")
	public void acessarURLdeBloqueioRepetido() {
		bloqueio.acessarURLBloqueioExpirado();
	}
	
	@Given("acesso a URL de bloqueio de nova conta com token não encontrado")
	public void acessarURLdeBloqueioNaoEncontrado() {
		bloqueio.acessarURLBloqueioNaoEncontrado();
	}

	@Given("ativar a conta pelo email")
	public void ativarAContaPeloEmail() {
		cadastroPages.ativarContaPeloEmail();
	}

	@Then("receberei uma mensagem de falha {string}")
	public void recebereiUmaMensagem(String falha) {
		assertEquals(falha , cadastroPages.obterMsgFalha());
	}
	
	@When("clico no botao de {string}")
	public void clicoNoBotaoDe(String botao) {
		cadastroPages.clicarNoBotaoDe(botao);
	}

	@Given("receberei uma mensagem de sucesso {string}")
	public void recebereiUmaMensagemDeSucesso(String mensagem) {
		assertEquals(mensagem, cadastroPages.obterMsgSucesso(mensagem));
	}
	
	@Given("valido o pin")
	public void validoOPin() {
		cadastroPages.cadastrarPin(token);
	}
	
	@Given("cadastro uma senha")
	public void cadastroUmaSenha() {
		cadastroPages.cadastrarSenha();   
	}

	@Then("finalizo o cadastro")
	public void finalizoOCadastro() { 
	}
	
	@When("informo minha senha")
	public void informoMinhaSenha() {
	   cadastroPages.informominhaSenha();
	}
	
	@Then("recebo a mensagem {string}")
	public void receboAMensagem(String string) {
		assertEquals("Você já possui uma conta Porto Seguro.", cadastroPages.validoMensagem());
	}
	
	@When("adiciono o token invalido")
	public void adicionoOPinInvalido() {
	   cadastroPages.pinInvalido();
	}
	
	@Then("recebo um {string}")
	public void receboUm(String mensagem) {
		assertEquals(mensagem , cadastroPages.ContaBloqueada());
	}
	
	@Then("receberei uma mensagem cpf inválido {string}")
	public void recebereiUmaMensagemCpfInválido(String mensagem) {
		assertEquals(mensagem , cadastroPages.msgCpfInvalido());
	}
	
	@When("cadastro senhas que nao coincidem")
	public void cadastroSenhasQueNaoCoincidem() {
	   cadastroPages.cadastroSenhasQueNaoCoincidem();
	}
	
	@When("sou direcionado para a pagina de inserir senha")
	public void validaPaginaSenha() {
	   cadastroPages.validaPaginaSenha();
	}

	@When("recebo o pin de cadastro via {string}")
	public void receboOPinDeCadastroVia(String emailOuSms) {
		if (emailOuSms.contains("yop")) {
			emailPages = new EmailPages();
			emailPages.newTab();
			emailPages.switchTab(1);
			emailPages.acessarEmail(emailOuSms);
			token = emailPages.obterPinCadastro();
			emailPages.deletarEmail();
			emailPages.closeTab();
			emailPages.switchTab(0);
			cadastroPages.cadastrarPin(token);

		} else {
			throw new cucumber.api.PendingException();
		}
	}
	
	@Given("recebo o primeiro pin de cadastro via {string}")
	public void validoOPrimeiroEnvidoDoPin(String emailOuSms) {
		if (emailOuSms.contains("yop")) {
			emailPages.newTab();
			emailPages.switchTab(1);
			emailPages.acessarEmail(emailOuSms);
			token = emailPages.obterPinCadastro();
			emailPages.deletarEmail();
			emailPages.closeTab();
			emailPages.switchTab(0);
		} else {
			
		}
	}
	
	@Given("recebo o primeiro pin de recuperacao via {string}")
	public void validoOPrimeiroEnvidoDoPinRecuperacao(String emailOuSms) {
		if (emailOuSms.contains("yop")) {
			emailPages.newTab();
			emailPages.switchTab(1);
			emailPages.acessarEmail(emailOuSms);
			token = emailPages.obterPinRecuperacao();
			emailPages.deletarEmail();
			emailPages.closeTab();
			emailPages.switchTab(0);
		} else {
			
		}
	}
	
	@When("recebo o pin de recueracao via {string}")
	public void receboOPinDeRecuperacaoVia(String emailOuSms) {
		if (emailOuSms.contains("yop")) {
			emailPages = new EmailPages();
			emailPages.newTab();
			emailPages.switchTab(1);
			emailPages.acessarEmail(emailOuSms);
			token = emailPages.obterPinRecuperacao();
			emailPages.deletarEmail();
			emailPages.closeTab();
			emailPages.switchTab(0);
			cadastroPages.cadastrarPin(token);

		} else {
			throw new cucumber.api.PendingException();
		}
	}
	
	@When("sou direcionao a pagina de bloqueio de nova conta")
	public void confirmaBloqueioNovaConta() {
		bloqueio = new BloqueioPage();
		bloqueio.switchTab(1);
		bloqueio.bloqueioNovaConta();
	}
	
	@When("sou direcionao a pagina de bloqueio de nova conta direto do navegador")
	public void confirmaBloqueioNovaContaNavegador() {
		bloqueio = new BloqueioPage();
		bloqueio.bloqueioNovaConta();
	}
	
	@When("recebo o link de bloqueio via {string}")
	public void receboLinkBloqueio(String email) {
		emailPages = new EmailPages();
		emailPages.newTab();
		emailPages.switchTab(1);
		emailPages.acessarEmail(email);
		emailPages.obterLinkBloqueio();
		emailPages.closeTab();
	}
	
	@When("recebo o segundo link de bloqueio via {string}")
	public void receboSegundoLinkBloqueio(String email) {
		BloqueioPage bloqueio = new BloqueioPage();
		bloqueio.navigateBack();
	}
	
	@When("recebo uma mensagem informando que as senhas não coincidem")
	public void mensagemSenhasNaoCoincidem() {
		assertEquals("Opa! As senhas não são iguais.", cadastroPages.validaMsgSenhasDiferentes());
	}
	
	@When("sou direcionado para a pagina de ja possui conta")
	public void confirmaContaExistente() {
		cadastroPages.confirmaContaExiste();
	}
	
	@When("sou direcionado para a tela de bloqueio por token expirado")
	public void confirmaTokenExpirado() {
		bloqueio.bloqueioTokenExpirado();
	}
	
	@When("sou direcionado a pagina de bloqueio de token reutilizado")
	public void confirmaTokenReutilizado() {
		bloqueio.bloqueioComTokenReutilizado();
	}
	
}
