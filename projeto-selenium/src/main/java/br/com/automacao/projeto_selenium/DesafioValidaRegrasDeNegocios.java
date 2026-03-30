package br.com.automacao.projeto_selenium;

import org.junit.Assert;
 
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DesafioValidaRegrasDeNegocios {
	/*
	 * 1 - nome é obrigatório
	 * 2 - sobrenome é obrigatório
	 * 3 - sexo é obrigatório
	 * 4 - não posso esolher simultaneamente  carne e vegetariano
	 * 5 -  não posso escolher um esporte e o que é esporte
	 */
	private WebDriver driver;
	
	@BeforeEach // realizado antes da execução de cada método
	public void inicializa() {
	    driver=new ChromeDriver(); //inicializa o driver do navegador em questão
		driver.manage().window().setSize(new Dimension(1200, 765));//seta o tamanho da aba de teste que sera aberta
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");// diz ao selenim um arquivo será pego
	}
	
	@AfterEach // realizado após a execução de cada método
	public void finaliza() {
		driver.quit();//fecha a guia atual
	}
	
	
	@Test
	//@Ignore
	public void veririficaNome() {
		
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alerta=driver.switchTo().alert();
		String msg=alerta.getText();
		
		Assert.assertEquals("Nome eh obrigatorio",msg);
		
		alerta.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Chico");
	}
	//@Ignore
	@Test
	public void verificaSobrenome() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Chico");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alerta=driver.switchTo().alert(); // mudar o foco para o alert aberto
		String msg=alerta.getText();
		
		Assert.assertEquals("Sobrenome eh obrigatorio", msg);
		
		alerta.accept(); // seleciona o ok do alert
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Bento");
		
	
	}
	
	@Test
	public void VerificaSelecaoSexo() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Chico");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Bento");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alerta=driver.switchTo().alert();
		String msg=alerta.getText();
		Assert.assertEquals("Sexo eh obrigatorio", msg);
		alerta.accept();
	}
	
	@Test
	public void verificaSelecaoComida() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Chico");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Bento");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alerta=driver.switchTo().alert();
		String msg=alerta.getText();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", msg);
		alerta.accept();
				
	} 
	
	@Test
	public void verificaPraticaEsporte() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Chico");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Bento");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		
		WebElement comboEscolaridade=driver.findElement(By.id("elementosForm:escolaridade"));
		Select listaEscolaridade=new Select(comboEscolaridade);
		listaEscolaridade.selectByVisibleText("Mestrado");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		WebElement comboEsporte=driver.findElement(By.id("elementosForm:esportes"));
		Select listaEsporte=new Select(comboEsporte);
		
		listaEsporte.selectByVisibleText("Natacao");
		listaEsporte.selectByVisibleText("O que eh esporte?");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alerta=driver.switchTo().alert();
		String msg=alerta.getText();
		
		Assert.assertEquals("Voce faz esporte ou nao?",msg);
		
		
	}


}
