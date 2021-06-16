package br.com.cyber.conquista.frameworkConquista.core.utils;

import static br.com.cyber.conquista.frameworkConquista.core.DriverFactory.getDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cyber.conquista.frameworkConquista.core.exceptions.UtilsException;
import br.com.cyber.conquista.frameworkConquista.core.interacao.Interactions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Utilitarios {

	private static final Logger log = LoggerFactory.getLogger(Utilitarios.class.getSimpleName());
	
	public static void fullPageScreenshot(String path, String nome) {
		log.info(String.format("Método: fullPageScreenshot() - Tirando Screenshot do arquivo %s", nome));

		try {
			new Interactions().frameDefault();
			Screenshot screenshot = new AShot()
					.shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(getDriver());
			
			ImageIO.write(screenshot.getImage(), "PNG", new File(String.format("%s_%s.png", path, nome)));
			log.info(String.format("Salvando a Printscreen no Caminho %s", path));
			log.info(String.format("O nome da Printscreen é %s", nome));

		} catch (IOException e) {
			throw new UtilsException(e, "");
		}
	}

	public static String formatter() {
		return DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss").format(LocalDateTime.now());
	}

	public static String caminhoScreenshot() {
		return "src" + File.separator + "test" + File.separator + "resources" + File.separator + "evidencias"
				+ File.separator + "screenshots" + File.separator;
	}

	public static void deletandoScreenshotsFile() {

		File fileSucesso = new File(caminhoScreenshot() + "sucesso");
		File fileErro = new File(caminhoScreenshot() + "erro");

		if (fileSucesso.listFiles().length > 0) 
			Arrays.stream(fileSucesso.listFiles()).forEach(File::delete);
		
		if (fileErro.listFiles().length > 0) 
			Arrays.stream(fileErro.listFiles()).forEach(File::delete);
	}

	public static void screenshotsCriarFiles() {

		File fileScreenshot = new File(caminhoScreenshot());
		File fileErro = new File(caminhoScreenshot() + "erro");
		File fileSucesso = new File(caminhoScreenshot() + "sucesso");

		fileScreenshot.mkdir();
		fileSucesso.mkdir();
		fileErro.mkdir();
	}
}
