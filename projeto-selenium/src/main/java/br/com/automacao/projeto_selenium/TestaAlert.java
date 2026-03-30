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
	
	dsl.clicarRadio("alert");
	Alert alert=driver.switchTo().alert();// muda o foco da tela para o novo modal de alert
	String textoAlerta=alert.getText();
	Assert.assertEquals("Alert Simples",textoAlerta);// compara o primeiro paramntro com a mensagem de texto do alert
	alert.accept(); // fecha o alert clicando no botão de ok
	dsl.escreve("elementosForm:nome",textoAlerta);
	

}
    @Test
    public void InterageComAlertConfirma() {
	
	dsl.clicarRadio("confirm");
	Alert alertConfirm=driver.switchTo().alert();
	alertConfirm.accept();//clica ok na mensagem de confirm
	Alert alertConfirmConfirmado=driver.switchTo().alert();
	Assert.assertEquals("Confirmado",alertConfirmConfirmado.getText());
	alertConfirm.accept();//clica na mensagem de confirm
	
	dsl.clicarRadio("confirm");
	Alert alertCancel=driver.switchTo().alert();
	alertCancel.dismiss();// clica no botão de cancelar
	Alert alertConfirmaCancel=driver.switchTo().alert();
	Assert.assertEquals("Negado",alertConfirmaCancel.getText());
    alertCancel.dismiss();//clica no ok do botão de cancelar
	
	
	
}

     @Test
     public void InterageComAlertPrompt() {
	
	driver.findElement(By.id("prompt")).click(); // cliquei no botão prompt que abre um alert
	
	Alert alert=driver.switchTo().alert();
	alert.sendKeys("55");
	alert.accept();
	Assert.assertEquals("Era 55?",alert.getText());
	alert.accept();
	Assert.assertEquals(":D",alert.getText());
	alert.accept();
}
}
