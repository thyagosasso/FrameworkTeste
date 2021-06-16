package br.com.cyber.conquista.frameworkConquista.pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cyber.conquista.frameworkConquista.core.BasePage;
import br.com.cyber.conquista.frameworkConquista.core.interacao.INavegador;

public class EmailPages extends BasePage implements INavegador {

	private static final Logger log = LoggerFactory.getLogger(EmailPages.class.getSimpleName());

	private String urlEmailYop = "http://www.yopmail.fr/en/";

	// YOPMail
	private By escreverEmail = By.id("login");
	private By btnChecarInbox = By.xpath("//input[@value='Check Inbox']");
//	private By primeiroEmail = By.xpath("/html/body/div/div[7]/div/a/span[1]/span[2]");

//	private By deleteEmail = By.xpath("//a[@title = 'Delete this mail (shortcut: press Del)']");

	private By linkBloqueio = By.xpath("//a[text()='Bloquear a Conta']");
	
	private By obterPinCadastro = By.xpath("//div[@class='mensagem_anterior']/strong");
	private By obterPinRecuperacao = By.xpath("//div/p");
	private By obterPinLogin = By.xpath("//div[@style='text-align:center;margin-top:30px;margin-bottom:30px;']/strong");

	// div[@class='mensagem_anterior']/strong
	public void acessarEmail(String email) {
		log.info("Metodo: acessarEmail() - Acessando e-mail :" + urlEmailYop + email);
		interactions.url(urlEmailYop);
		interactions.textClear(escreverEmail, "Limpando o campo");
		interactions.write(escreverEmail, email, "escreverEmail");
		interactions.click(btnChecarInbox, "btnProximoPasso");

	}

	public String obterPinCadastro() {
		log.info("Metodo: obterPinCadastro() - Obtendo pin de cadastro");
		String pin = "";
		interactions.switchFrame("ifmail", "Frame yopmail");
		pin = interactions.getText(obterPinCadastro, "obterPinEmail").trim();
		System.out.println(pin);
		return pin;
	}

	public String obterPinLogin() {
		log.info("Metodo: obterPinLogin() - Obtendo pin de login");
		String pin = "";
		interactions.switchFrame("ifmail", "Frame yopmail");
		pin = interactions.getText(obterPinLogin, "obterPinEmail").trim();
		System.out.println(pin);
		return pin;
	}

	public void deletarEmail() {
		log.info("Metodo: deletarEmail() - Deletando o último email");
		interactions.frameDefault();
		interactions.click(By.xpath("//a[@title = 'Delete this mail (shortcut: press Del)']/.."), "clicando no X");
	}

	public String obterPinRecuperacao() {
		log.info("Metodo: obterPinRecuperacao() - Obtendo pin de recuperação");
		String pin = "";
		interactions.switchFrame("ifmail", "Frame yopmail");
		pin = interactions.getText(obterPinRecuperacao, "obterPinEmail " + obterPinRecuperacao).trim();
		System.out.println(pin);
		return pin;
	}
	
	public void obterLinkBloqueio() {
		log.info("Método: obterLinkBloqueio() - Obtendo link de bloqueio");
		interactions.switchFrame("ifmail", "Frame yopmail");
		interactions.click(linkBloqueio, "Link de bloqueio");
	}
}
