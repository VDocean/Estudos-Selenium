package br.com.projeto.test;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.projeto.core.DSL;
import br.com.projeto.core.DriverFactory;

public class TestaAlert {
	
private WebDriver driver;
private DSL dsl;

	@BeforeEach // realizado antes da execução de cada método
	public void inicializa() {
		DriverFactory.getDriver();
		// file/// indica que é uma url local , System.getProperty("user.dir") retorna o diretorio de trabalho atual
		
	}
	
	
	@AfterEach // realizado após a execução de cada método
	public void finaliza() {
		DriverFactory.killDriver();//fecha o navegador
	}
	
	@Test

    public void deveInteragirComAlertSimples() {
	
	dsl.clicarBotao("alert");
	String textoAlerta=dsl.alertaObterTextoEAceita();
	Assert.assertEquals("Alert Simples",textoAlerta);// compara o primeiro paramntro com a mensagem de texto do alert
	
	

}
    @Test
    public void InterageComAlertConfirma() {
	
	dsl.clicarBotao("confirm");
	dsl.alertaObterTextoEAceita();
	String textoAlerta=dsl.alertaObterTextoEAceita();
	Assert.assertEquals("Confirmado",textoAlerta);
	
	
	dsl.clicarBotao("confirm");
	dsl.alertaObterTextoENega();
	textoAlerta=dsl.alertaObterTextoENega();
	Assert.assertEquals("Negado",textoAlerta);
   
	
	
	
}

     @Test
     public void InterageComAlertPrompt() {
    	 
	dsl.clicarBotao("prompt");
	dsl.alertaEscrever("55");
	dsl.AlertSomenteAceita();
	String textoAlert=dsl.alertaObterTextoEAceita();
	Assert.assertEquals("Era 55?",textoAlert);
	textoAlert=dsl.alertaObterTextoEAceita();
	Assert.assertEquals(":D",textoAlert);
	
}
}
