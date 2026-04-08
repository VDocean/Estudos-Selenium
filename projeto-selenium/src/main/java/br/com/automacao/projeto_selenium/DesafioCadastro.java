package br.com.automacao.projeto_selenium;
import org.junit.Assert;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DesafioCadastro {
	
	WebDriver driver;
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
	public void preencheDadosCadastro() {
		
		dsl.escrever("elementosForm:nome","Chico");
		dsl.escrever("elementosForm:sobrenome","Bento"); 
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		dsl.clicarRadio("elementosForm:sexo:1"); 
		dsl.selecionarCombo("elementosForm:escolaridade","2o grau completo");
		dsl.selecionarCombo("elementosForm:esportes","Natacao");
		dsl.selecionarCombo("elementosForm:esportes","Futebol");
		dsl.selecionarCombo("elementosForm:esportes","Corrida");
		dsl.clicarBotao("elementosForm:cadastrar");
		 		
		//Validações
		
		Assert.assertEquals("Chico",dsl.obterValorCampo("elementosForm:nome"));
		Assert.assertEquals("Bento",dsl.obterValorCampo("elementosForm:sobrenome"));
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:1"));
		Assert.assertEquals("2o grau completo",dsl.obterValorCombo("elementosForm:escolaridade", "2o grau completo"));
		
	}
	

}
