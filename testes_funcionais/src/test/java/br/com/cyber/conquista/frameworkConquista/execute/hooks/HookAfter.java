package br.com.cyber.conquista.frameworkConquista.execute.hooks;

import static br.com.cyber.conquista.frameworkConquista.core.DriverFactory.killDriver;
import static br.com.cyber.conquista.frameworkConquista.core.utils.Utilitarios.caminhoScreenshot;

import java.io.File;

import br.com.cyber.conquista.frameworkConquista.core.Properties;
import static br.com.cyber.conquista.frameworkConquista.core.utils.Utilitarios.*;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;

public class HookAfter {
	
	@After(order = 1)
	public void screenshot(Scenario scenario) {

		String nome = String.format("%s_%s", scenario.getName().replace(" ", "_"), formatter()); 
		String evidencia = (!scenario.isFailed()) ? evidencia = "sucesso" : "erro";
		String caminho = caminhoScreenshot() + evidencia + File.separator;

		fullPageScreenshot(caminho, nome);
	}

	@After(order = 0)
	public void finalizar() {
		if (Properties.FECHAR_BROWSER)
			killDriver();
	}
}
