package br.com.cyber.conquista.frameworkConquista.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cyber.conquista.frameworkConquista.core.BasePage;

public class LoginPages extends BasePage {
	private static final Logger log = LoggerFactory.getLogger(LoginPages.class.getSimpleName());

	private By btnCpfCnpj = By.xpath("//input[@id='mat-input-0']");
	private String urlLogin = "http://otdighomm.portoseguro.brasil/pdc/login";
	private By msgCpfInvalido = By.xpath("//p/strong");
	private By senha = By.xpath("//input[@id='mat-input-1']");
	private By msgFalha = By.xpath("/html/body/main/div/div/pdc-root/section/section/section/pdc-cadastro-page/pdc-web-container/div/div[2]/pdc-input-cpf-cnpj/mat-form-field/div/div[3]/div/mat-error/p/strong");
	private By aceitarTermos = By.xpath("//div/input[@id='mat-checkbox-2-input']/..");
	private By sucesso = By.xpath("//p[contains(text(), 'Ainda')]");
	private By ativarEmail = By.xpath("//div[contains(text(), '@')]");
	private By bloqueio = By.xpath("//h2[contains(text(), 'Bloqueada')]");
	private By textoCadastrar = By.xpath("//h2");
	private By msgSenhaInvalida = By.xpath("//p/strong");
	private By botaoVoltar = By.xpath("//img[@alt='voltar']");
	private By textoHomepage = By.xpath("//a/p[text()='WhatsApp']");
	private By msgCadastroSucesso = By.xpath("//h1");
	private By esqueciASenha = By.xpath("//a[contains(text(), 'Esqueceu')]");
	private By senhaRecuperacao = By.xpath("//input[@id='mat-input-2']");
	private By validaSenhaRecuperacao = By.xpath("//input[@id='mat-input-3']");
	private By confirmarFluxoCadastro = By.xpath("//h2[text()='Ativar Conta']");
	private By confirmarPaginaInicialCadastro = By.xpath("//h2[text()='Cadastrar Conta']");
	private By criarNovaConta = By.xpath("//a");
	private By enviarPinNovamente = By.xpath("//a[contains(text(),'Enviar novamente')]");
	private By enviarPinEmail = By.xpath("//a[contains(text(), '-mail')]");
	private By ativarSMS = By.xpath("//span[contains(text(), '(11)')]");
	
	private By irParaPaginaDeTermos = By.xpath("//a");
	private By confirmarPaginaDeTermos = By.xpath("//h2");
	private By confirmarHomeLogada = By.xpath("//p[contains(text(), 'Minha Conta')]");
	
	
	private By btnAceitarCookies = By.xpath("//button[contains(@id, 'accept-btn')]");
	
	
	public void AcessandoAPaginaDeLogin() {
		log.info("Metodo: cadastro() - Acessando URL Cadastro " + urlLogin);
		interactions.url(urlLogin);
	}

	public void inserirCpfCnpj(String valor) {
		isButtonDisabled();
		log.info("Metodo: cpf() - Escrevendo CPF " + btnCpfCnpj);
		disabledAndWrite(btnCpfCnpj, valor, "Input CPF");
}

	public void escreverSenha() {
		log.info("Metodo: escreverSenha() " + senha);
		interactions.awaitElement(senha, "Senha");
		isButtonDisabled();
		disabledAndWrite(senha, "senha123", "Botão de senha");;
	}

	public String obterMsgFalha() {
		log.info("Metodo: msgErro() - Obtendo Titulo " + msgFalha);
		String texto = null;
		texto = interactions.getText(msgFalha, "Poxa! Não conseguimos localizar.");
		return texto;
	}

	public void aceitarTermos() {
		log.info("Metodo: aceitarTermos() - Aceitando termos");
		isButtonDisabled();
		interactions.awaitElement(aceitarTermos, "Aceitar termos");
		interactions.click(aceitarTermos, "Aceitar termos");
	}

	public void confirmarLoginSucesso() {
		log.info("Metodo: confirmarLoginSucesso() - Verificando a tela de sucesso");
		interactions.awaitElement(sucesso, 10, "Confirmar login sucesso");
		interactions.scroll(sucesso, "Rolando a tela até o elemento " + sucesso);
		interactions.awaitElement(sucesso, "Confirmar login sucesso");
		String confirmacao = interactions.getText(sucesso, "Obtendo texto do elemento " + sucesso);
		Assert.assertEquals("Ainda não tem sua conta Porto Seguro?", confirmacao);
	}

	public void confirmarBloqueio() {
		log.info("Metodo: confirmarBloqueio() - Verificando a tela de bloqueio");
		interactions.awaitElement(bloqueio, 10, "tela de Bloqueio");
		String confirmacao = interactions.getText(bloqueio, "Obtendo texto do elemento " + bloqueio);
		Assert.assertEquals("Conta Bloqueada", confirmacao);
	}

	public void ativarContaEmail() {
		log.info("Metodo: ativarContaEmail() - " + ativarEmail);
		isButtonDisabled();
		interactions.awaitElement(ativarEmail, "Ativar email");
		interactions.click(ativarEmail, "ativarEmail " + ativarEmail);
	}

	public String msgCpfInvalido() {
		log.info("Método: msgCpfInvalido() - " + msgCpfInvalido);
		interactions.click(textoCadastrar, "clicar para msg");
		interactions.awaitElement(msgCpfInvalido, "Mensagem de erro");
		String texto = null;
		texto = interactions.getText(msgCpfInvalido, "CPF inválido");
		return texto;
	}

	public void inserirSenhaInvalida() {
		log.info("Método: inserirSenhaErrada()");
		interactions.write(senha, "senha1222", "btnSenha");
	}

	public void excederLimiteSenhaInvalida() {
		log.info("Método: inserirSenhaErrada()");
		
		interactions.writeSlowly(senha, "123", "Senha curta");
		isButtonDisabled();
		
		for (int i = 0; i < 4; i++) {
			
			interactions.writeSlowly(senha, "senha1234" + i, "Senha inválida");
			clicarNoBotaoDe("Entrar");
		}
	}

	public void msgSenhaInvalida(String mensagem) {
		log.info("Método: msgSenhaInvalida()");
		interactions.awaitElement(msgSenhaInvalida, "Mensagem de senha inválida");
		String msg = interactions.getText(msgSenhaInvalida, "Obtendo msg de Senha Inválida");
		Assert.assertEquals(mensagem, msg);
	}

	public void botaoVoltar() {
		log.info("Método: botaoVoltar()");
		interactions.wait(1000);
		interactions.awaitElement(botaoVoltar, "Botão de Voltar");
		interactions.click(botaoVoltar, "Clicando no Botão voltar");
	}

	public void confirmarHomePage() {
		log.info("Método: confirmarHomePage() - Verficando a Homepage");
		interactions.pageValidation(textoHomepage, "WHATSAPP", "Homepage");
	}

	public void confirmarCadastroSucesso() {
		log.info("Metodo: confirmarCadastroSucesso() - Verificando a tela de Sucesso no Cadastro de Senha");
		interactions.awaitElement(msgCadastroSucesso, "Mensagem de Sucesso");
		String confirmacao = interactions.getText(msgCadastroSucesso, "Mensagem de Sucesso");
		Assert.assertEquals("Tudo certo com seu cadastro!", confirmacao);
	}

	public void esqueciASenha() {
		log.info("Metodo: esqueciASenha() - Clicando no botão de 'Esqueci a senha'");
		interactions.click(esqueciASenha, "Clicando no elemento " + esqueciASenha);

	}

	public void escreverSenhaRecuperacao() {
		log.info("Metodo: escreverSenhaRecuperacao() - Escrevendo a senha de recuperação");
		interactions.awaitElement(senhaRecuperacao, 10, "Senha de Recuperação");
		isButtonDisabled();
		interactions.writeSlowly(senhaRecuperacao, "senha123", "Escrevendo a senha");
		interactions.writeSlowly(validaSenhaRecuperacao, "senha123", "Validando a senha");
	}

	public String confirmarFluxoCadastro() {
		log.info("Metodo: confirmarFluxoCadastro()");
		interactions.awaitElement(confirmarFluxoCadastro, "Confirmação de Fluxo de Cadastro");
		return interactions.getText(confirmarFluxoCadastro, "Confirmação de Fluxo de Cadastro");
	}
	
	public String confirmarPaginaInicialCadastro() {
		log.info("Metodo: confirmarPaginaInicialCadastro()");
		return interactions.getText(confirmarPaginaInicialCadastro, "Obtendo texto do elemento " + confirmarPaginaInicialCadastro);
	}

	public void clicarCriarNovaConta() {
		log.info("Metodo: confirmarPaginaInicialCadastro()");
		interactions.scroll(criarNovaConta, "Scrollando até o elemento " + criarNovaConta);
		interactions.click(criarNovaConta, "Clicando no botão de criar nova conta " + criarNovaConta);
	}

	public void enviarPinNovamente() {
		log.info("Metodo: enviarPinNovamente()");
		interactions.awaitElement(enviarPinNovamente, 10, "Enviar pin novamente");
		interactions.scroll(enviarPinNovamente, "Scrollando até o elemento " + enviarPinNovamente);
		interactions.click(enviarPinNovamente, "Clicando no botão de enviar pin novamente " + enviarPinNovamente);
	}

	public void ativarContaSMS() {
		log.info("Metodo: ativarContaEmail() - " + ativarSMS);
		isButtonDisabled();
		interactions.awaitElement(ativarSMS, "Ativação por sms");
		interactions.click(ativarSMS, "ativarEmail " + ativarSMS);
	}

	public void enviarPinEmail() {
		log.info("Metodo: enviarPinEmail()");
		interactions.scroll(enviarPinEmail, "Scrollando até o elemento " + enviarPinEmail);
		interactions.click(enviarPinEmail, "ativarEmail " + enviarPinEmail);
	}
	
	public void abrirTermos() {
		log.info("Método: abrirTermos()");
		interactions.awaitElement(irParaPaginaDeTermos, "Página de termos");
		interactions.click(irParaPaginaDeTermos, "Página de termos");
	}
	
	public String confirmarPaginaDeTermos() {
		log.info("Método: confirmarPaginaDeTermos()");
		interactions.scroll(confirmarPaginaDeTermos, "Scrollando até o elemento " + confirmarPaginaDeTermos);
		return interactions.getText(confirmarPaginaDeTermos, "Obtendo texto do Elemento " + confirmarPaginaDeTermos);
	}
	
	public void aceitarCookies() {
		log.info("Método: aceitarCookies()");
		interactions.switchTab(1);
		interactions.frameDefault();
		interactions.awaitElement(btnAceitarCookies, "Aceitando cookies");
		interactions.click(btnAceitarCookies, "Aceitando cookies");
	}

	public void confirmaHomeLogada() {
		log.info("Método: confirmaHomeLogada() - Validando a Home Logada");
		interactions.pageValidation(confirmarHomeLogada, "Minha Conta", "minha conta");
	}
	
}
