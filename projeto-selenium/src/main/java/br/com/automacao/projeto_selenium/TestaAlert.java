package br.com.automacao.projeto_selenium;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestaAlert {
	
private WebDriver driver;
private DSL dsl;

	@BeforeEach // realizado antes da execução de cada método
	public void inicializa() {
		driver=new ChromeDriver(); 
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html"); // essa linha pede para o driver buscar uma url
		// file/// indica que é uma url local , System.getProperty("user.dir") retorna o diretorio de trabalho atual
		dsl=new DSL(driver);
	}
	
	
	@AfterEach // realizado após a execução de cada método
	public void finaliza() {
		driver.quit();//fecha o navegador
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
