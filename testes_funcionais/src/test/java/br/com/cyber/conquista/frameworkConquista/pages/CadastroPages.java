package br.com.cyber.conquista.frameworkConquista.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cyber.conquista.frameworkConquista.core.BasePage;
import br.com.cyber.conquista.frameworkConquista.core.exceptions.InteracaoException;

public class CadastroPages extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(CadastroPages.class.getSimpleName());

	private By btnCpfCnpj = By.id("mat-input-0");
	private String urlCadastro = "http://otdighomm.portoseguro.brasil/pdc/cadastro";

	private By ativarEmail = By.xpath(
			"/html/body/main/div/div/pdc-root/section/section/section/pdc-recovery-channel/div/pdc-web-container/div/div/pdc-email-phone-selector/div/section/mat-radio-group/div[1]/div/mat-radio-button/label/div[1]/div[1]");
	private By ativarCelular = By.xpath(
			"/html/body/main/div/div/pdc-root/section/section/section/pdc-recovery-channel/div/pdc-web-container/div/div/pdc-email-phone-selector/div/section/mat-radio-group/div[2]/div/mat-radio-button/label/div[1]/div[1]");
	private By msgFalha = By.xpath(
			"/html/body/main/div/div/pdc-root/section/section/section/pdc-cadastro-page/pdc-web-container/div/div[2]/pdc-input-cpf-cnpj/mat-form-field/div/div[3]/div/mat-error/p/strong");
	private By msgSucesso = By.xpath("//span[contains(text(),'Pronto!') or contains(text(),'ops')]");
	private By obterPinEmail = By.xpath("/html/body/div/div[3]/div[2]/div[2]/div/div[2]/strong");
	private By campoPin = By.xpath("//input[@data-gtm-name]");
	private By btnContinuar = By.xpath("//span[contains(text(),'Continuar')]");
	private By btnSenha = By.xpath("//input[@id='mat-input-1']");
	private By btnConfirmaSenha = By.xpath("//input[@id='mat-input-2']");
	private By btnPcadastro = By.xpath(
			"/html/body/main/div/div/pdc-root/section/section/section/pdc-register-password/pdc-web-container/div/div[2]/div/pdc-btn-standard/button");
	private By campoSenhaEntrar = By.xpath(
			"/html/body/main/div/div/pdc-root/section/section/section/pdc-password-page/pdc-web-container/div/div[2]/pdc-input-password/mat-form-field/div/div[1]/div[1]/input");
	private By msgCadastrado = By.xpath(
			"/html/body/main/div/div/pdc-root/section/section/section/pdc-registered-user-page/pdc-web-container/div/div[2]/p[1]");
	private By statusNaoCliente = By.xpath("");
	private By msgValidacaocadastro = By.xpath("");
	private By msgContaBloqueada = By
			.xpath("/html/body/main/div/div/pdc-root/section/section/section/pdc-message-page/div/div/div[1]/h2");
	private By msgCpfInvalido = By.xpath(
			"/html/body/main/div/div/pdc-root/section/section/section/pdc-cadastro-page/pdc-web-container/div/div[2]/pdc-input-cpf-cnpj/mat-form-field/div/div[3]/div/mat-error/p/strong");
	private By textoCadastrar = By.xpath(
			"/html/body/main/div/div/pdc-root/section/section/section/pdc-cadastro-page/pdc-web-container/div/div[2]/pdc-title-subtitle/div/div[1]/h2");
	private By validaPaginaSenha = By.xpath("//p[contains(text(), 'senha')]");

	private By senhaAvisoStrong = By.xpath("//strong[contains(text(), 'Opa!')]");
	private By cadastroConfirmaContaP = By.xpath("//p[contains(text(), 'Você já possui uma conta')]");

	public void AcessandoAPaginaDeCadastro() {
		log.info("Metodo: cadastro() - Acessando URL Cadastro " + urlCadastro);
		interactions.url(urlCadastro);
	}

	public void cadastrarConta(String valor) {
		log.info("Metodo: cpf() - Escrevendo cpf");
		disabledAndWrite(btnCpfCnpj, valor, "Input CPF");
	}

	public void ativarContaPeloEmail() {
		log.info("Metodo: ativarEmail() " + ativarEmail);
		interactions.click(ativarEmail, "ativarEmail");
	}

	public String obterMsgFalha() {
		log.info("Metodo: msgErro() - Obtendo Titulo " + msgFalha);
		String texto = null;
		texto = interactions.getText(msgFalha, "Poxa! Não conseguimos localizar.");
		return texto;
	}

	public String obterMsgSucesso(String texto) {
		log.info("Metodo: msgSucesso() " + msgSucesso);
		By mensagemDeslizante= By.xpath(String.format("//span[contains(text(),'%s')]", texto));
		interactions.awaitElement(mensagemDeslizante, 10, "Mensagem de sucesso");
		return interactions.getText(mensagemDeslizante, "mensagem deslizante");
	}

	public void cadastrarPin(String pin) {
		log.info(String.format("Método: cadastrarPin() - Inserindo o token %s para validação", pin));
		disabledAndWrite(campoPin, pin, "Input de Token");
		interactions.awaitElement(btnContinuar, "Botão de continuar");
		interactions.click(btnContinuar, "btnContinuar");
	}

	public void cadastrarSenha() {
		log.info("Metodo: btnSenha()" + btnSenha);
		interactions.awaitElement(btnSenha, 10, "Senha");
		isButtonDisabled();
		interactions.write(btnSenha, "senha123", "Senha");
		interactions.write(btnConfirmaSenha, "senha123", "Confirmação de senha");
	}

	public void informominhaSenha() {
		log.info("Metodo: campoSenhaEntrar()" + campoSenhaEntrar);
		interactions.write(campoSenhaEntrar, "senha123", "campoSenhaEntrar");
	}

	public String validoMensagem() {
		log.info("mesagem de já cadastrado " + msgCadastrado);
		String texto = null;
		texto = interactions.getText(msgCadastrado, "Você já possui uma conta Porto Seguro.");
		return texto;
	}

	public String validoStatus() {
		log.info("status conta excluida " + statusNaoCliente);
		String texto = null;
		texto = interactions.getText(statusNaoCliente, "");
		return texto;
	}

	public String validoCadastro() {
		log.info("" + msgValidacaocadastro);
		String texto = null;
		texto = interactions.getText(msgValidacaocadastro, "");
		return texto;
	}

	public void pinInvalido() {
		interactions.awaitElement(campoPin, 10, "Campo de token");
		for (int i = 0; i < 5; i++) {
			interactions.wait(5000);
			interactions.write(campoPin, "1234567", "Pin inválido");
			clicarNoBotaoDe("Continuar");
		}
	}

	public String ContaBloqueada() {
		log.info("" + msgContaBloqueada);
		String texto = null;
		texto = interactions.getText(msgContaBloqueada, "");
		return texto;
	}

	public String msgCpfInvalido() {
		log.info("CPF inválido." + msgCpfInvalido);
		interactions.click(textoCadastrar, "clicar para msg");
		interactions.awaitElement(msgCpfInvalido, "Mensagem de cpf inválido");
		interactions.scroll(msgCpfInvalido, "scroll para mensagem");
		String texto = null;
		texto = interactions.getText(msgCpfInvalido, "CPF inválido.");
		return texto;
	}

	public void cadastroSenhasQueNaoCoincidem() {
		log.info("Metodo: btnSenha()" + btnSenha);
		interactions.awaitElement(btnSenha, 10, "Senha");
		isButtonDisabled();
		interactions.writeSlowly(btnSenha, "senha123", "btnSenha");
		interactions.writeSlowly(btnConfirmaSenha, "senha111", "btnConfirmaSenha");
		interactions.click(By.xpath("//h2"), "Saindo do input");
	}

	public void validaPaginaSenha() {
		log.info("Método: validaPaginaSenha() - Verficando a página de senha");
		interactions.pageValidation(validaPaginaSenha, "Digite a sua senha", "Validação da página de senha");
	}
	
	public String validaMsgSenhasDiferentes() {
		log.info("Método: validaMsgSenhasDiferentes() - Verificando a mensagem das senhas diferentes");
		isButtonDisabled();
		interactions.awaitElement(senhaAvisoStrong, "Mensagem de senhas diferentes");
		return interactions.getText(senhaAvisoStrong , "Mensagem de senhas diferentes");
	}

	public void confirmaContaExiste() {
		log.info("Método: confirmaContaExiste() - Verificando a página de conta existente");
		interactions.awaitElement(cadastroConfirmaContaP, "Mensagem de possui conta");
		interactions.pageValidation(cadastroConfirmaContaP, "Você já possui uma conta", "Mensagem de possui conta");
	}
}
