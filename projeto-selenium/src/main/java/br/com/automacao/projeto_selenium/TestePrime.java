package br.com.automacao.projeto_selenium;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestePrime {
    private WebDriver driver;
	private DSL dsl;
	
	@BeforeEach // realizado antes da execução de cada método
	public void inicializa() {
		driver=new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("https://showcase.primefaces.org/ui/ajax/dropdown.xhtml?jfwid=c821a"); // essa linha pede para o driver buscar uma url
		// file/// indica que é uma url local , System.getProperty("user.dir") retorna o diretorio de trabalho atual
        dsl=new DSL(driver);	
	}
	
	@AfterEach // realizado após a execução de cada método
	public void finaliza() {
		//driver.quit();//fecha o navegador
	}
	
	@Test
	public void PrimeItens() {
		
	    //dsl.clicarRadio(By.xpath("//label[text()='Option1']/preceding-sibling::div//div[contains(@class,'ui-radiobutton-box')]"));

		dsl.selecionarComboPrime("//span[contains(@id,'country_label')]","Brazil");
	   
			
	}

}
