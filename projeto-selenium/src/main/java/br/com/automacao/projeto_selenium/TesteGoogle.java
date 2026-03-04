package br.com.automacao.projeto_selenium;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import junit.framework.Assert;
 
public class TesteGoogle {
	
@Test	
//essa notação do Junit declara que o metodo a seguir é um test
	public void test() {
	 
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765)); // define dimensões do brownser ao abrir
		//driver.manage().window().fullscreen();
		driver.get("https://www.google.com.br/index.html");
		Assert.assertEquals("Google", driver.getTitle());//compara se o valor da primeiro parametro é igual ao recebido no segundo parametro
		driver.quit();//fecha o navegador
		//driver.close();//fecha uma aba
	}

}
