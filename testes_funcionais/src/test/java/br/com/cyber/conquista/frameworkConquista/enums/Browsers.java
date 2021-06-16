package br.com.cyber.conquista.frameworkConquista.enums;

import static br.com.cyber.conquista.frameworkConquista.core.Headless.chromeOptions;
import static br.com.cyber.conquista.frameworkConquista.core.Headless.firefoxOptions;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum Browsers {
	
	CHROME("chrome") {
		@Override
		public WebDriver newDriver(SistemaOperacional so, boolean interfaceGrafica) {
			this.setProperty(so);
			return new ChromeDriver(chromeOptions(interfaceGrafica));
		}
	},
	FIREFOX("gecko") {
		@Override
		public WebDriver newDriver(SistemaOperacional so, boolean interfaceGrafica) {
			this.setProperty(so);
			return new FirefoxDriver(firefoxOptions(interfaceGrafica));
		}
	};
	
	private String descricao;
	 
	private Browsers(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
    
    public abstract WebDriver newDriver(SistemaOperacional so, boolean interfaceGrafica);
    
    protected void setProperty(SistemaOperacional so) {
    	System.setProperty("webdriver."+ this.getDescricao() +".driver", 
				System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "drivers"
				+ File.separator + so.getDescricao() + File.separator + this.getDescricao() + "driver" + so.getExtensao());
	}
}
