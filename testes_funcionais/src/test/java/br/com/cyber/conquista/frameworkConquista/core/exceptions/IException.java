package br.com.cyber.conquista.frameworkConquista.core.exceptions;

import static br.com.cyber.conquista.frameworkConquista.core.utils.Utilitarios.formatter;
import static br.com.cyber.conquista.frameworkConquista.enums.ExceptionsMessages.*;

import java.io.IOException;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cyber.conquista.frameworkConquista.core.utils.InputDeDados;

public interface IException {

	public static final Logger log = LoggerFactory.getLogger(InteracaoException.class.getSimpleName());
	
	public default String handler(Exception e, String message) {
		
		if (e instanceof NoSuchElementException) message = NO_SUCH_ELEMENT.getException(message);

		else if (e instanceof TimeoutException) message = TIMEOUT.getException(message);

		else if (e instanceof ElementNotVisibleException) message = NOT_VISIBLE.getException(message);

		else if (e instanceof StaleElementReferenceException) message = STALE_REFERENCE.getException(message);

		else if (e instanceof ElementNotInteractableException) message = NOT_INTERACTABLE.getException(message);

		else if (e instanceof WebDriverException) message = WEBDRIVER.getException(message);

		else if (e instanceof NoSuchFrameException) message = NO_SUCH_FRAME.getException(message);

		else if (e instanceof InterruptedException) message = INTERRUPTED.getException(message);
		
		else if (e instanceof IOException) message = IO_EXCEPTION.getException(message);

		else if (e instanceof Exception) message = EXCEPTION.getException(message);
		
		return message;
	}

	public default void logger(Exception e, String message) {
		message = (!message.contains("  ")) ? message : message.concat(System.lineSeparator() + "Você esqueceu de descrever o elemento.");

		log.error(String.format(
				System.lineSeparator() 
				+ System.lineSeparator()
				+ "                    !!! UM ERRO ACONTECEU !!!" 
				+ System.lineSeparator() 
				+ System.lineSeparator()
				+ "======================= MENSAGEM AMIGÁVEL =======================" 
				+ System.lineSeparator()
				+ System.lineSeparator() 
				+ message
				+ System.lineSeparator() 
				+ System.lineSeparator()
				+ "======================= DESCRIÇÃO TÉCNICA =======================" 
				+ System.lineSeparator()
				+ System.lineSeparator() 
				+ "%s - %s" 
				+ System.lineSeparator() 
				+ System.lineSeparator()
				+ "============================= DICAS =============================" 
				+ System.lineSeparator()
				+ System.lineSeparator() 
				+ "Verifique se o seu Xpath está correto." 
				+ System.lineSeparator()
				+ "Verifique se não existe um Iframe nessa página." 
				+ System.lineSeparator()
				+ "Verifique se seu PageObject foi instanciado corretamente em caso de nullPointer."
				+ System.lineSeparator() 
				+ System.lineSeparator()
				+ "====================== QUE OS JOGOS COMECEM ======================" 
				+ System.lineSeparator()
				+ System.lineSeparator(), formatter(), e.getMessage()));
	}
	
	public default String description(String message) {
		
		return 
		"============================ ERRO ============================" 
		+ System.lineSeparator()
		+ System.lineSeparator() 
		+ this.getClass().getSimpleName() + ": " + message 
		+ System.lineSeparator()
		+ System.lineSeparator() 
		+ "====================== MASSA UTILIZADA ======================"
		+ System.lineSeparator() 
		+ System.lineSeparator() 
		+ InputDeDados.getInput() 
		+ System.lineSeparator()
		+ System.lineSeparator() 
		+ "=========================== DICAS ==========================="
		+ System.lineSeparator() 
		+ System.lineSeparator() 
		+ "Verifique se o seu Xpath está correto."
		+ System.lineSeparator() 
		+ "Verifique se não existe um Iframe nessa página." 
		+ System.lineSeparator()
		+ "Verifique se seu PageObject foi instanciado corretamente em caso de nullPointer."
		+ System.lineSeparator();
	}
}
