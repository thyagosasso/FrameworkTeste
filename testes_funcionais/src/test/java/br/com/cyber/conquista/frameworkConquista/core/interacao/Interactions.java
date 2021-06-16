package br.com.cyber.conquista.frameworkConquista.core.interacao;

import static br.com.cyber.conquista.frameworkConquista.core.DriverFactory.getDriver;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cyber.conquista.frameworkConquista.core.exceptions.InteracaoException;

/**
 * <p>
 * Classe de <i>interações</i> genéricas para ser utilizada no padrão
 * <strong>Page Object</strong>.
 * 
 * @author Felipe Gadelha
 * @author Jonathan Daflon
 * @author Thyago Sasso
 * @since 1.0
 * @see InteracaoException
 */
public class Interactions implements INavegador {

	public static final Logger log = LoggerFactory.getLogger(Interactions.class.getSimpleName());

	/**
	 * <p>
	 * <strong>Função:</strong> acessar uma URL.
	 * 
	 * @param url {@link String string}.
	 * @return {@link Void void}.
	 */
	public void url(String url) {
		log.info(String.format("Método: url() - Acessando o endereço: %s.", url));

		try {
			getDriver().get(url);

		} catch (Exception e) {
			throw new InteracaoException(e, url);
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>escrever()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> escreve uma {@link String string} no elemento
	 * especificado.
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param text        é a {@link String string} a ser escrita no elemento.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 */
	public void write(By by, String text, String description) {
		log.info(String.format("Método: write() - Escrevendo '%s' no elemento %s.", text, description));
		try {
			textClear(by, description);
			getDriver().findElement(by).sendKeys(text);

		} catch (InteracaoException e) {
			throw e;

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>escreverSlow()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> escreve lentamente uma {@link String string} no
	 * elemento especificado.
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param text        é a {@link String string} a ser escrita no elemento.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 */
	public void writeSlowly(By by, String text, String description) {
		log.info(
				String.format("Método: writeSlowly() - Escrevendo lentamente '%s' no elemento %s.", text, description));
		try {
			textClear(by, description);
			WebElement txtValor = getDriver().findElement(by);
			List<String> list = Arrays.asList(text.split(""));
		
			list.forEach(
						letter -> {
							txtValor.sendKeys(letter);
							wait(100);
						}
					);
			
		} catch (InteracaoException e) {
			throw e;

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}

	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>limpar()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> limpa o texto no elemento especificado.
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 */
	public void textClear(By by, String description) throws InteracaoException {
		log.info(String.format("Método: textClear() - Apagando o texto no elemento: %s.", description));
		try {
			getDriver().findElement(by).clear();
		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>clicar()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> clica no elemento especificado.
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 */
	public void click(By by, String description) {
		log.info(String.format("Método: click() - Clicando no elemento %s", description));
		try {
			getDriver().findElement(by).click();

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>isRadioMarcado()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> verifica se o elemento especificado está
	 * selecionado/marcado.
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Boolean boolean}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 */
	public boolean isRadioSelected(By by, String description) {
		log.info(String.format("Método: isRadioSelected() - Verificando se o elemento %s está marcado/selecinado.",
				description));
		try {
			return getDriver().findElement(by).isSelected();

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>obterTexto()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> obtém, loga e retorna o valor textual do elemento
	 * especificado ou {@link Null null} em caso de falha.
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link String string}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 */
	public String getText(By by, String description) {
		log.info(String.format("Método: getText() - Obtendo texto do elemento %s.", description));
		try {
			String text = getDriver().findElement(by).getText();
			log.info(String.format("O texto obtido foi: %s.", text));
			return text;

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>validaPagina()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> Valida se a página atual é a página desejada e
	 * verifica se a mesma está carregada.
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param text        é a {@link String string} validadora à ser comparada com o
	 *                    texto obtido do elemento.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 * @see #awaitElement
	 * @see #getText(By, String)
	 */
	public void pageValidation(By by, String text, String description) {
		log.info(String.format("Método: pageValidation() - Validando a página atual pelo elemento: %s.", description));
		try {
			awaitElement(by, 45, description);
			Assert.assertTrue("A página falhou no processo de verificação.", getText(by, description).contains(text));

		} catch (InteracaoException e) {
			throw e;

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>obterTitleElemento()</li>
	 * <li>obterLinkElemento()</li>
	 * <li>obterClasseElemento()</li>
	 * <li>obterReadOnlyElemento()</li>
	 * </ul>
	 * 
	 * <p>
	 * <strong>Função:</strong> esta interação tem a função de retornar um atributo
	 * específico de um elemento ou {@link Null null} caso o atributo não exista.
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param attribute   é o {@link String atributo} que será obtido.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link String string}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 */
	public String getAttribute(By by, String attribute, String description) {
		log.info(String.format("Método: getAttribute() - Obtendo o atributo %s do elemento: %s.", attribute,
				description));
		try {
			String text = getDriver().findElement(by).getAttribute(attribute);
			log.info(String.format("O atributo obtido foi: %s.", text));
			return text;

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * @deprecated
	 *             <p>
	 *             Ao invés disso utilize o método
	 *             {@link #awaitElement(By, String)}.
	 *             <p>
	 *             <strong>Substitui:</strong>
	 *             <ul>
	 *             <li>aguarda()</li>
	 *             </ul>
	 *             <p>
	 *             <strong>Função:</strong> faz com que a thread entre no modo
	 *             "sleep" por um determinado período de tempo.
	 * @param milissegundos um {@link Integer int} em milissegundos.
	 * @return {@link Void void}
	 * @throws InterruptedException caso o estado dormente seja interrompido.
	 * @see InteracaoException
	 */
	public void wait(int milissegundos) {
		log.info(String.format("Método: wait() - Aguardando implicitamente por %.1f segundos",
				((float) milissegundos / 1000)));
		try {
			Thread.sleep(milissegundos);
		} catch (Exception e) {
			throw new InteracaoException(e, "");
		}
	}

	/**
	 * <p>
	 * <strong>Função:</strong> compara duas unidades de tempo em segundos e retorna
	 * uma {@link String mensagem} descrevendo o tempo decorrido.
	 * 
	 * @param before um {@link Instant momento} anterior no tempo.
	 * @param after  um {@link Instant momento} posterior no tempo.
	 * @return {@link String string}
	 * @see #awaitElement(By, String)
	 * @see #awaitElement(By, Integer, String)
	 */
	public String compareTime(Instant before, Instant after) {
		log.info("Método: compareTime() - Calculando o tempo de carregamento.");
		Long time = ChronoUnit.SECONDS.between(before, after);
		return "O tempo decorrido foi de: " + time.toString();
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>esperarElemento()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> aguarda o tempo de carregamento informado de um
	 * elemento, lançando uma exceção caso esse tempo seja ultrapassado. Também
	 * informa quanto tempo levou para que o elemento fosse carregado em caso de
	 * sucesso.
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param seconds     é a quantidade de {@link Integer segundos} para aguardar.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 * @see #compareTime(Instant, Instant)
	 * @see #awaitElement(By, String)
	 */
	public void awaitElement(By by, Integer seconds, String description) {
		Instant before = Instant.now();
		log.info(String.format("Método: awaitElement() - Aguardando o elemento %s por %s segundos.", description,
				seconds.toString()));

		try {
			new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.visibilityOf(getDriver().findElement(by)));
			log.info(compareTime(before, Instant.now()));

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>esperarElemento()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> aguarda o tempo de carregamento de um elemento por
	 * 15 segundos, lançando uma exceção caso esse tempo seja ultrapassado. Também
	 * informa quanto tempo levou para que o elemento fosse carregado em caso de
	 * sucesso.
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 * @see #compareTime(Instant, Instant)
	 * @see #awaitElement(By, Integer, String)
	 */
	public void awaitElement(By by, String description) {
		Instant before = Instant.now();
		log.info(String.format("Método: awaitElement() - Aguardando o elemento %s por 15 segundos.", description));

		try {
			new WebDriverWait(getDriver(), 15).until(ExpectedConditions.visibilityOf(getDriver().findElement(by)));
			log.info(compareTime(before, Instant.now()));

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>elementExists()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> verifica se o elemento especificado está visível na
	 * tela.
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Boolean boolean}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 */
	public boolean isElementDisplayed(By by, String description) {
		log.info(String.format("Método: isElementDisplayed() - Verificando se o elemento %s está visível.",
				description));
		try {
			return getDriver().findElement(by).isDisplayed();

		} catch (Exception e) {
//			throw new InteracaoException(e, description);
			return false;
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>listaParaClicks()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> cria uma lista de elementos e clica em um deles
	 * aleatoriamente.
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param description é o {@link String nome} geral do elementos.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 */
	public void randomClickList(By by, String description) {
		log.info(String.format("Método: randomClickList() - Gerando uma lista randomica para clicks nos elementos %s.",
				description));
		try {

			List<WebElement> elementos = getDriver().findElements(by);
			log.info(String.format("O número de elementos é", elementos.size()));
			Integer n = (int) (Math.random() * (elementos.size() - 1));
			log.info(String.format("Selecionando o elemento de número %s.", n.toString()));
			elementos.get(n).click();

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>combo()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> seleciona um elemento de uma lista dropdown (combo)
	 * através do atributo "value" (e não do texto aparente).
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param value       é o {@link String valor} da opção do combo (e não o seu
	 *                    texto).
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 */
	public void selectComboByValue(By by, String value, String description) {
		log.info(
				String.format("Método: selectComboByValue() - Selecionando o elemento %s do combo atráves do valor %s.",
						description, value));
		try {

			new Select(getDriver().findElement(by)).selectByValue(value);

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Função:</strong> manipula um elemento do tipo slider apenas em seu
	 * eixo horizontal. Caso esse método não funcione, experimente o
	 * {@link #sliderSendKeys(By, Integer, String)}
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param number      é o {@link Integer número} utilizado para quantificar o
	 *                    movimento do elemento.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 * @see #sliderSendKeys(By, Integer, String)
	 */
	public void slider(By by, Integer number, String description) {
		log.info(
				String.format("Método: slider() - movendo o elemento %s %s unidades.", description, number.toString()));
		try {

			new Actions(getDriver()).dragAndDropBy(getDriver().findElement(by), number, 0).build().perform();

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>sliderComSeta()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> manipula um elemento do tipo slider apenas em seu
	 * eixo horizontal utilizando o método sendKeys para a direita. Caso esse método
	 * não funcione, experimente o {@link #slider(By, Integer, String)}
	 * 
	 * @param by          é o {@link By seletor} do elemento.
	 * @param repetitions é a {@link Integer quantidade} de vezes que o comando de
	 *                    seta será enviada.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 * @see #slider(By, Integer, String)
	 */
	public void sliderSendKeys(By by, Integer repetitions, String description) {
		log.info(String.format("Método: sliderSendKeys() - movendo o elemento %s com %s repetições de sendKeys.",
				description, repetitions.toString()));
		try {

			click(by, "Clicando no Slider");
			for (int i = 0; i <= repetitions; i++) {
				getDriver().findElement(by).sendKeys(Keys.ARROW_RIGHT);
			}

		} catch (InteracaoException e) {
			throw e;

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>entrarFrame()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> troca o frame atual pelo index especificado no
	 * parâmetro.
	 * 
	 * @param index       é o {@link Integer index} do frame a ser acessado.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 * @see #switchFrame(String, String)
	 */
	public void switchFrame(Integer index, String description) {
		log.info(String.format("Método: entrarFrame() - no elemento índice %s.", index.toString()));
		try {

			getDriver().switchTo().frame(index);

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Substitui:</strong>
	 * <ul>
	 * <li>entrarFrame()</li>
	 * </ul>
	 * <p>
	 * <strong>Função:</strong> troca o frame atual pelo nome especificado no
	 * parâmetro.
	 * 
	 * @param frame       é o {@link String nome} do frame a ser acessado.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 * @see #switchFrame(Integer, String)
	 */
	public void switchFrame(String frame, String description) {
		log.info(String.format("Método: entrarFrame() - no elemento de nome %s.", description));
		try {

			getDriver().switchTo().frame(frame);

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	/**
	 * <p>
	 * <strong>Função:</strong> retorna para o frame do conteúdo principal.
	 * 
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteracaoException
	 */
	public void frameDefault() {
		log.info("Método: frameDefault() - Voltando ao conteúdo principal.");
		String description = "Frame Default";

		try {

			getDriver().switchTo().defaultContent();

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	public void waitOptions(ExpectedCondition<?> expectedCondition, String description) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 15);
		try {
			wait.until(expectedCondition);

		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}

	public void sendKeys(By by, Keys key, String description) {
		log.info("Método: sendKeys() - Enviando comandos de tecla para um elemento.");
		try {
			getDriver().findElement(by).sendKeys(key);
		} catch (Exception e) {
			throw new InteracaoException(e, description);
		}
	}
}
