package br.com.automacao.projeto_selenium;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;



public class TesteSincronismo {
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
		//driver.quit();//fecha o navegador
	}
	
	//@Test
public void deveUltilizarEsperaFixa() throws InterruptedException {

		
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escrever("novoCampo","Deu certo?");
	}
//@Test
public void deveUltilizarEsperaImplicita() throws InterruptedException {

	
	dsl.clicarBotao("buttonDelay");
	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);// verifica durante 5 segundos se a funciobnalidade esta disponivel e interrompe a contagem assim que estiver liberada
	dsl.escrever("novoCampo","Deu certo?");
	driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
}

@Test
public void deveUltilizarEsperaExplicita() throws InterruptedException {

	
	dsl.clicarBotao("buttonDelay");
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
	dsl.escrever("novoCampo","Deu certo?");
	
}

}




	 
 
	
    