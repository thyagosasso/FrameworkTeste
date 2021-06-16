package br.com.cyber.conquista.frameworkConquista.execute.steps;

import static org.junit.Assert.assertEquals;

import br.com.cyber.conquista.frameworkConquista.pages.BloqueioPage;
import br.com.cyber.conquista.frameworkConquista.pages.EmailPages;
import br.com.cyber.conquista.frameworkConquista.pages.LoginPages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	private LoginPages loginPages = new LoginPages();
	private BloqueioPage bloqueio = new BloqueioPage();
	private EmailPages emailPages = new EmailPages();
	private String pin;

	@When("que um usuário acessou a opção de login")
	public void queUmUsuárioAcessouAOpçãoDeLogin() {
		loginPages = new LoginPages();
		loginPages.AcessandoAPaginaDeLogin();
	}

	@When("digito o {string}")
	public void digitoO(String cpfCnpj) {
		loginPages.inserirCpfCnpj(cpfCnpj);
	}

	@When("digito a senha")
	public void digitoASenha() {
		loginPages.escreverSenha();
	}

	@When("digito a senha de recuperacao")
	public void digitoASenhaDeRecuperacao() {
		loginPages.escreverSenhaRecuperacao();
	}

	@Then("receberei uma falha {string}")
	public void recebereiUmaFalha(String falha) {
		assertEquals(falha, loginPages.obterMsgFalha());
	}

	@When("seleciono validacao por {string}")
	public void selecionoValidacaoPor(String emailOuSms) {

		if (emailOuSms.contains("@")) {
			loginPages.ativarContaEmail();
		} else if (emailOuSms.contains("(11)")) {
			loginPages.ativarContaSMS();
		}
	}

	@Given("recebo o primeiro pin de login via {string}")
	public void validoOPrimeiroEnvidoDoPin(String emailOuSms) {
		if (emailOuSms.contains("@")) {
			emailPages.newTab();
			emailPages.switchTab(1);
			emailPages.acessarEmail(emailOuSms);
			pin = emailPages.obterPinLogin();
			emailPages.deletarEmail();
			emailPages.closeTab();
			emailPages.switchTab(0);
		} else {

		}
	}

	@Given("recebo o pin de login via {string}")
	public void validoOPin(String emailOuSms) {
		if (emailOuSms.contains("@")) {
			emailPages.newTab();
			emailPages.switchTab(1);
			emailPages.acessarEmail(emailOuSms);
			pin = emailPages.obterPinLogin();
			emailPages.deletarEmail();
			emailPages.closeTab();
			emailPages.switchTab(0);
			loginPages.cadastrarPin(pin);
		} else {

		}
	}

	@Given("recebo o pin de recuperacao via {string}")
	public void validoOPinDeRecuperacao(String emailOuSms) {
		if (emailOuSms.contains("@")) {
			emailPages = new EmailPages();
			emailPages.newTab();
			emailPages.switchTab(1);
			emailPages.acessarEmail(emailOuSms);
			pin = emailPages.obterPinRecuperacao();
			emailPages.deletarEmail();
			emailPages.closeTab();
			emailPages.switchTab(0);
			loginPages.cadastrarPin(pin);

		} else {
			throw new cucumber.api.PendingException();
		}
	}

	@When("aceito os termos de politica de privacidade")
	public void aceitoOsTermosDePoliticaDePrivacidade() {
		loginPages.aceitarTermos();
	}

	@Then("sou direcionado para a pagina de atividade")
	public void sereiDirecionadoParaAPaginaDeAtividade() {
		loginPages.confirmarLoginSucesso();
	}

	@Then("sou direcionado para a pagina de conta bloqueada")
	public void sereiDirecionadoParaAPaginaDeContaBloqueada() {
		loginPages.confirmarBloqueio();
	}

	@Then("sou direcionado para a tela de bloqueio do lexis")
	public void telaDeBloqueioLexis() {
		bloqueio.bloqueioLexis();
	}

	@Then("clico em voltar")
	public void clicarNaLogo() {
		bloqueio.voltarAHome();
	}

	@Then("sou direcionado para a tela de bloqueio por tentativa")
	public void telaDeBloqueioTentativa() {
		bloqueio.bloqueioPadrao();
	}

	@When("digito uma senha invalida")
	public void digitoUmaSenhaInvalida() {
		loginPages.inserirSenhaInvalida();
	}

	@Then("recebo uma {string} de falha")
	public void recebereiUmaMensagemDeFalha(String mensagem) {
		assertEquals(mensagem, loginPages.msgCpfInvalido());
	}

	@Then("recebo uma {string} senha invalida")
	public void recebereiUmaMensagemDeSenhaInvalida(String mensagem) {
		loginPages.msgSenhaInvalida(mensagem);
	}

	@Then("eu excedo as tentativas de login")
	public void euExcedoAsTentativasDeLogin() {
		loginPages.excederLimiteSenhaInvalida();
	}

	@Then("clico no botao de voltar")
	public void clicoNoBotaoDeVoltar() {
		loginPages.botaoVoltar();
	}

	@Then("sou direcionado para a homepage")
	public void souDirecionadoParaAHomepage() {
		loginPages.confirmarHomePage();
	}

	@Then("sou direcionado para a pagina de tudo certo com cadastro")
	public void souDirecionadoParaAPaginadeSucesso() {
		loginPages.confirmarCadastroSucesso();
	}

	@When("clico no link de esqueceu senha")
	public void clicoNoLinkDeEsqueceuSenha() {
		loginPages.esqueciASenha();
	}

	@Then("sou direcionado para o fluxo de cadastro")
	public void souDirecionadoParaOFluxoDeCadastro() {
		assertEquals("Ativar Conta", loginPages.confirmarFluxoCadastro());
	}

	@Then("eu clico no link de criar nova conta")
	public void clicoNoBotaoDeCriarNovaConta() {
		loginPages.clicarCriarNovaConta();
	}

	@Then("sou direcionado para a pagina inicial de cadastro")
	public void confirmarPaginaIncialDeCadastro() {
		assertEquals("Cadastrar Conta", loginPages.confirmarPaginaInicialCadastro());
	}

	@Then("clico em enviar pin novamente")
	public void enviarPinNovamente() {
		loginPages.enviarPinNovamente();
	}

	@Then("clico em enviar pin por email")
	public void enviarPinPorEmail() {
		loginPages.enviarPinEmail();
	}

	@Then("clico no link de politica de privacidade")
	public void clicarEmPoliticaDePrivacidade() {
		loginPages.abrirTermos();
	}

	@Then("sou direcionado para a pagina de politica de privacidade")
	public void souDirecionadoParaAPaginaDeTermos() {
		loginPages.aceitarCookies();
		assertEquals("Resumindo nossa atual Política de Privacidade", loginPages.confirmarPaginaDeTermos().trim());
		new EmailPages().closeTab();
		new EmailPages().switchTab(1);
	}

	@Then("insiro um Pin do banco de dados")
	public void inserirPin() {
		throw new cucumber.api.PendingException();
	}

	@Then("sou direcionado para a pagina de recuperar conta bloqueada")
	public void souDirecionadoAPaginadeBloqueioPorTentativa() {
		bloqueio.bloqueioTentativa();
	}
	
	@Then("sou direcionado para a home logada")
	public void souDirecionadoParaHomeLogada() {
		loginPages.confirmaHomeLogada();
	}
	
}
