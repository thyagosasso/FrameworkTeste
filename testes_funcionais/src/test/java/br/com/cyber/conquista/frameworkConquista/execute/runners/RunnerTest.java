package br.com.cyber.conquista.frameworkConquista.execute.runners;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import br.com.cyber.conquista.frameworkConquista.core.utils.Utilitarios;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
		glue = "br.com.cyber.conquista.frameworkConquista.execute",

		tags = { 
					"@loginClienteAtivoLexisPass, @loginClienteAtivoLexisReview"
				},
		
		plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"json:src/test/resources/evidencias/json/report.json", },
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		dryRun = false, 
		strict = true
		)

public class RunnerTest {

	@BeforeClass
	public static void screenshotFileCheck() {
		Utilitarios.screenshotsCriarFiles();
		Utilitarios.deletandoScreenshotsFile();
	}
}
