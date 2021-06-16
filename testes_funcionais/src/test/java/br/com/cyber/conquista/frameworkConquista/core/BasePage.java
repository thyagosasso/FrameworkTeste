package br.com.cyber.conquista.frameworkConquista.core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cyber.conquista.frameworkConquista.core.exceptions.InteracaoException;
import br.com.cyber.conquista.frameworkConquista.core.interacao.Interactions;

import static br.com.cyber.conquista.frameworkConquista.core.DriverFactory.getDriver;


/**
 * Uma classe base para todos os PageObjects.
 * 
 * @author Felipe Gadelha
 * @author Jonathan Daflon
 * @author Thyago Sasso
 *
 */
public abstract class BasePage {

	private static final Logger log = LoggerFactory.getLogger(BasePage.class.getSimpleName());
	
	private String button = "//span[text()=' %s ']";
	private By disabledLink = By.xpath("//a[@class='pdc-link ng-star-inserted disabled']");
	private By disabledButton = By.xpath("//button[@disabled]");
	private By campoPin = By.xpath("//input");
	private By link = By.xpath("//div[@class='web-container']//a");
	protected Interactions interactions;

	public BasePage() {
		interactions = new Interactions();
	}

	public void clicarNoBotaoDe(String buttonName) {
		try {
			By buttonBy = By.xpath(String.format(button, buttonName));
			
			boolean validation = interactions.isElementDisplayed(link, "");
			
			waitAndScroll(buttonBy, buttonName);
			clickAndDisable(buttonBy, buttonName, validation);
			
		} catch (Exception e) {
			throw new InteracaoException(e, buttonName);
		}
	}
	
	public void botaoComKeys(By by) {
		try {
			interactions.wait(2000);
			interactions.sendKeys(by, Keys.ENTER, "");
		} catch (Exception e) {
			throw new InteracaoException(e, "");
		}
	}
	public void disabledAndWrite(By by, String text, String description) {
		isButtonDisabled();
		interactions.wait(500);
		interactions.write(by, text, description);
	}

	private void waitAndScroll(By button, String buttonName) {
		interactions.waitOptions(ExpectedConditions.elementToBeClickable(button), String.format("Botão %s", buttonName));
		interactions.scroll(button, String.format("Scroll até o botão %s", buttonName));
	}
	
	public void isButtonDisabled() {
		log.info("Método: isButtonDisabled() - Verificando se o botão está desabilitado.");
		Assert.assertTrue("Botão não está desabilitado", interactions
				.isElementDisplayed(disabledButton, "Botão desabilitado"));
	}
	
	private void isLinkDisabled(boolean link) {
		log.info("Método: isLinkDisabled() - Verificando se o link está desabilitado.");
		
		if(link) {
			Assert.assertTrue("Link não está desabilitado", interactions.isElementDisplayed(disabledLink, "Link desabilitado"));
		}
	}
	
	private void clickAndDisable(By by, String buttonName, boolean link) {
		interactions.click(by, String.format("Clicando no botão %s", buttonName));
		isButtonDisabled();
		isLinkDisabled(link);
	}
	
	public void cadastrarPin(String pin) {
		log.info(pin);
		interactions.write(campoPin, pin, "Campo de pin");
	}
	
	public String mensagemDeEnvioDeToken() {
		
		By mensagem = By.xpath("//span[contains(text(),'Pronto!') or contains(text(),'Falha') or contains(text(),'erro')]");
		
		int contador = 0;
		interactions.awaitElement(mensagem, "Mensagem");
		String text = interactions.getText(mensagem, "Mensagem");
		
		if(contador < 3 || text.contains("Pronto!")) return text;
		
		else if (contador < 3 && !text.contains("Pronto!")) {
			contador++;
			mensagemDeEnvioDeToken();
		} 
			
		return "";
		
	}
}