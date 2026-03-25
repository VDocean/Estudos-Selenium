package br.com.automacao.projeto_selenium;

import org.openqa.selenium.Dimension;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;


public class TesteFramesEJanelas {
	
	WebDriver driver;
	
	@BeforeEach // realizado antes da execução de cada método
	public void inicializa() {
		driver=new ChromeDriver(); 
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html"); // essa linha pede para o driver buscar uma url
		// file/// indica que é uma url local , System.getProperty("user.dir") retorna o diretorio de trabalho atual
	}
	
	
	@AfterEach // realizado após a execução de cada método
	public void finaliza() {
		driver.quit();//fecha o navegador
	}
	
	@Ignore
	public void InteragirComFrames() {
		 
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		
	    Alert alert=driver.switchTo().alert();
	    
	    String msg=alert.getText();
	    
	    Assert.assertEquals("Frame OK!", msg);
	    
	    alert.accept();
	    
	   driver.switchTo().defaultContent();
	   
	   driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
	   	
	
	}
	
	

	@Ignore
	//interage com janelas sem nome
	public void deveInteragirComJanelas(){
		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup"); // troco o o foco do drive para a janela que abriu de nome Popup
		driver.findElement(By.tagName("textarea")).sendKeys("Área de texto"); // escrevo dentro da área de texto da nova janela
		
		driver.close();
		driver.switchTo().window(""); // retorno o foco do driver para a janela principal
		driver.findElement(By.tagName("textarea")).sendKeys("Área de texto");// dentro da área de texto da página principal escrevo uma mensagem
		
	}
	
	
	@Test
	//interage com janelas sem título ou sem nome
	
	public void deveInteragirComJanelasSemtítulos() {
	
		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle()); // retorna o identificador da aba que está em primeiro plano
		System.out.println(driver.getWindowHandles());// retorna o identificador de todas as abas abertas
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]); // foca o driver na página do identifacador de posição 0
		driver.findElement(By.tagName("textarea")).sendKeys("Escrevendo na área de texto");
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);// foca o driver na página do identifacador de posição 1
		driver.findElement(By.tagName("textarea")).sendKeys("Escrevendo na área de texto");
		
	}

}
