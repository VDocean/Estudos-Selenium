package br.com.automacao.projeto_selenium;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class TesteCampoTreinamento {
	 
	WebDriver driver=new ChromeDriver();
	
	
	@Test
	public void testeInterageTextField() {
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html"); // essa linha pede para o driver buscar uma url
		// file/// indica que é uma url local , System.getProperty("user.dir") retorna o diretorio de trabalho atual
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value")); // verifica se o valor do campo é igual ao esperad
		
		driver.quit();//fecha o navegador
        
		
	}
	@Test
	public void testeInterageTextArea() {
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("testes\nPula uma linha");
		Assert.assertEquals("testes\nPula uma linha",driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
		driver.quit();//fecha o navegador
		
	}
	
	@Test
	public void testeInterageRadioButtons() {
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		driver.quit();
	}
	
	@Test
	public void testeInterageCheckBox() {
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		driver.quit();
	}
	
	@Test 
	public void deveVerificarValoresLista() {
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		WebElement element=driver.findElement(By.id("elementosForm:escolaridade")); // aqui crio uma varável do tipo WebElement que é o tipo para qualquer retorno de infromação de uma página 
		Select lista=new Select(element);// uso a classe select pois ela tem um conjunto de métodos para manipulação de elementos do tipo select ou lista suspensas em html
		List<WebElement> options=lista.getOptions();
		Assert.assertEquals(8,options.size());
		
		//verifica uma consulta de nome de opção
		boolean encontrou=false;	
		for (WebElement option:options) {
			if (option.getText().equals("1o grau incompleto")) {
				encontrou=true;
				break;
			}
		
		}
		Assert.assertTrue(encontrou);
		driver.quit();
		
		}
	@Test 
		public void deveVerificarValoresComboMultiplo(){
			driver.manage().window().setSize(new Dimension(1200, 765));
			driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
			WebElement element=driver.findElement(By.id("elementosForm:esportes")); // aqui crio uma varável do tipo WebElement que é o tipo para qualquer retorno de infromação de uma página 
			Select lista=new Select(element);
			//Múltipla seleção de uma lista
			lista.selectByVisibleText("Futebol");
			lista.selectByVisibleText("Corrida");
			lista.selectByVisibleText("Karate");
			
			
			//Verifica se a quantidade de opçoes selecionadas é igual a 3
			List<WebElement> AllOptionsSelected=lista.getAllSelectedOptions();
			Assert.assertEquals(3,AllOptionsSelected.size());
			
			//verifica se a quantidade de item selecionados é 2 após desselecionar 1
			
			lista.deselectByVisibleText("Karate");
			List<WebElement> AllOptionsSelectedTwo=lista.getAllSelectedOptions();
			Assert.assertEquals(2,AllOptionsSelectedTwo.size());
			driver.quit();
			
		}
	
	    
	@Test 
		public void deveInteragirComBotoes() {
			driver.manage().window().setSize(new Dimension(1200, 765));
			driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
			WebElement botao=driver.findElement(By.id("buttonSimple"));
			botao.click();
			Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
			driver.quit();
		}
	    
	@Test 
	//@Ignore--> ignotra o teste
	public void deveInteragirComLinks() {
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		driver.findElement(By.linkText("Voltar")).click();
		driver.quit();
	}
	
	public void deveBuscarTextosNaPagina(){
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));// verifica se é verdade
		//que dentro do corpo da pagina existe o nome "Campo Treinamento"
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());// verifica se o nome dentro da tag h3 é igual a campo de treinamento
		
		
		driver.quit();
		
	}

}
