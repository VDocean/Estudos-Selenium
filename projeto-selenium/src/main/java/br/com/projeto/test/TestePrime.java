package br.com.projeto.test;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.projeto.core.DSL;
import br.com.projeto.core.DriverFactory;

public class TestePrime {
    private WebDriver driver;
	private DSL dsl;
	
	@BeforeEach // realizado antes da execução de cada método
	public void inicializa() {
		DriverFactory.getDriver().get("\"file:///\" + System.getProperty(\"user.dir\") + \"/src/main/resources/componentes.html\"");
        dsl=new DSL();	
	}
	
	@AfterEach // realizado após a execução de cada método
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void PrimeItens() {
		
	    //dsl.clicarRadio(By.xpath("//label[text()='Option1']/preceding-sibling::div//div[contains(@class,'ui-radiobutton-box')]"));

		dsl.selecionarComboPrime("//span[contains(@id,'country_label')]","Brazil");
	   
			
	}

}
