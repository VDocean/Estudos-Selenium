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


public class DesafioCadastro {
	
	@Test
	public void preencheDadosCadastro() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Chico");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Bento");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		
		WebElement element=driver.findElement(By.id("elementosForm:escolaridade")); 
		Select lista=new Select(element); 
		lista.selectByValue("mestrado");
		
		WebElement elementMultiSelect=driver.findElement(By.id("elementosForm:esportes"));
		Select listaMultiSelect=new Select(elementMultiSelect);
		listaMultiSelect.selectByVisibleText("Natacao");
		listaMultiSelect.selectByVisibleText("Futebol");
		listaMultiSelect.selectByVisibleText("Corrida");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		//Validações
		
		Assert.assertEquals("Chico",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		Assert.assertEquals("Bento", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:1")).isSelected());
		Assert.assertEquals(3,(listaMultiSelect.getAllSelectedOptions()).size());
		
	}
	

}
