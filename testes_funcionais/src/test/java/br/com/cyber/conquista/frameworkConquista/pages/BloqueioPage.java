package br.com.cyber.conquista.frameworkConquista.pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cyber.conquista.frameworkConquista.core.BasePage;
import br.com.cyber.conquista.frameworkConquista.core.interacao.INavegador;

public class BloqueioPage extends BasePage implements INavegador {

	private static final Logger log = LoggerFactory.getLogger(BloqueioPage.class.getSimpleName());

	public void bloqueioLexis() {
		log.info("Método: bloqueioLexis() - Verficando a tela de bloqueio do Lexis");
		interactions.pageValidation(By.xpath("//p[contains(text(), 'Por motivos')]"), "Por motivos", "Tela de bloqueio do Lexis");
	}

	public void bloqueioPadrao() {
		log.info("Método: bloqueioPadrao() - Verificando a tela de bloqueio padrão");
		interactions.pageValidation(By.xpath("//p[text() = ' O bloqueio é uma medida padrão que mantém sua conta em segurança. ']"), "O bloqueio", "Tela de bloqueio por tentativa");
	}
	
	public void bloqueioTentativa() {
		log.info("Método: bloqueioTentaiva() - Verificando a tela de bloqueio por tentativa");
		interactions.pageValidation(By.xpath("//span[text()=' Recuperar Conta ']"), "Recuperar Conta", "Botão de recuperação");
	}

	public void bloqueioNovaConta() {
		log.info("Método: bloqueioNovaConta() - Verificando a tela de bloqueio de Nova Conta");
		interactions.pageValidation(By.xpath("//span[contains(@class, 'sub') and contains(text(), 'Ao continuar')]"), "Ao continuar", "Tela de Bloqueio de Nova Conta");
	}
	
	public void bloqueioTokenExpirado() {
		log.info("Método: bloqueioTokenExpirado() - Verificando a tela de bloqueio de Nova Conta com token expirado");
		interactions.pageValidation(By.xpath("//h2[contains(text(), 'Expirada')]"), "Expirada", "Tela de bloqueio de token expirado");
	}
	
	public void bloqueioComTokenReutilizado() {
		log.info("Método: bloqueioComTokenReutilizado() - Verificando a tela de bloqueio de Nova Conta com token reutilizado");
		interactions.pageValidation(By.xpath("//h2[contains(text(), 'Já Bloqueada')]"), "Já Bloqueada", "Tela de bloqueio de token reutilizado");
	}
	
	public void voltarAHome() {
		log.info("Método: voltarAHome() - Voltando à Home");
		interactions.click(By.xpath("//a"), "Botão de voltar");
	}
	
	public void acessarURLBloqueioNaoEncontrado() {
		log.info("Método: acessarURLBloqueioNovaConta() - Acessando a Tela de bloqueio");
		interactions.url("http://otdighomm.portoseguro.brasil/pdc/bloquear/conta?sggcod=3D63D83721036833D90D241AF52AE83EAE96E55BEB5B79A39CE282195E69D851715344C35C4C7E21A887138851A6BEE8376E4832485F2BE1A9A4690C405AEC3AEB50D754659DDB398443AD&sggcodsrc=3B662C22D2E7FAAD9A3E5A8026B9B9F8DFABD045C8FAE0C4B9A039DDAEA20801A5EAC2E4254810FE32DACFFACD5DEC563106D4357E63130105DB565AA1A240EDE4C975D027851390D0CBB1D878515091CB1E5D");
	}
	
	public void acessarURLBloqueioExpirado() {
		log.info("Método: acessarURLBloqueioNovaConta() - Acessando a Tela de bloqueio");
		interactions.url("http://otdighomm.portoseguro.brasil/pdc/bloquear/conta?sggcod=160F0C408793C140CF3A200973669AD02A3C295B07628DA64EDB100BE9315F3184340FA523716C5892343D7A8C67FC9525C9DFA7C3BE6A6D5D91D36F540C2417B30363B0F2E57F6B1014B9&sggcodsrc=4254D0F5CB694FE2E57194B0297C378F797F2CC74EDDF9C2DADD5B4115950A97F2FF18010E7ED9BFE651B6A2450FB3928FD5B4F538EA07D46DB1D75B67E21E66A8298C7B94A81866C9BDD06F4D9F84AFECBDAB");
	}
}
