package br.com.automacao.projeto_selenium;
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class TestaAlert {
	
@Test

public void deveInteragirComAlertSimples() {
	
	WebDriver driver=new ChromeDriver();
	driver.manage().window().setSize(new Dimension(1200, 765));
	driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
	driver.findElement(By.id("alert")).click();
	Alert alert=driver.switchTo().alert();// muda o foco da tela para o novo modal de alert
	String textoAlerta=alert.getText();
	Assert.assertEquals("Alert Simples",textoAlerta);// compara o primeiro paramntro com a mensagem de texto do alert
	
	alert.accept(); // fecha o alert clicando no botão de ok
	
	driver.findElement(By.id("elementosForm:nome")).sendKeys(textoAlerta); // poe a mensagem de texto do alert no campo elementosForm:nome
	driver.quit();
}
	
@Test
public void InterageComAlertConfirma() {
	
	WebDriver driver=new ChromeDriver();
	driver.manage().window().setSize(new Dimension(1200, 765));
	driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
	driver.findElement(By.id("confirm")).click();
	Alert alertConfirm=driver.switchTo().alert();
	alertConfirm.accept();//clica ok na mensagem de confirm
	Alert alertConfirmConfirmado=driver.switchTo().alert();
	Assert.assertEquals("Confirmado",alertConfirmConfirmado.getText());
	alertConfirm.accept();//clica na mensagem de confirm
	
	driver.findElement(By.id("confirm")).click();
	Alert alertCancel=driver.switchTo().alert();
	alertCancel.dismiss();// clica no botão de cancelar
	Alert alertConfirmaCancel=driver.switchTo().alert();
	Assert.assertEquals("Negado",alertConfirmaCancel.getText());
    alertCancel.dismiss();//clica no ok do botão de cancelar
	
	
	
}

@Test
public void InterageComAlertPrompt() {
	WebDriver driver=new ChromeDriver();
	driver.manage().window().setSize(new Dimension(1200, 765));
	driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
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
