package br.com.projeto.test;

import java.util.List;

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

import br.com.projeto.core.DSL;
import br.com.projeto.core.DriverFactory;

public class TesteCampoTreinamento {
	 
 
	
 
	private DSL dsl;
	
	@BeforeEach // realizado antes da execução de cada método
	public void inicializa() {
		DriverFactory.getDriver();
        dsl=new DSL();	
	}
	
	@AfterEach // realizado após a execução de cada método
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void testeInterageTextField() {
		
	    dsl.escrever("elementosForm:nome","Teste de escrita");
		Assert.assertEquals("Teste de escrita",dsl.obterValorCampo("elementosForm:nome")); // verifica se o valor do campo é igual ao esperad
			
	}
	@Test
	public void testeInterageTextArea() {
		dsl.escrever("elementosForm:sugestoes", "testes\nPula uma linha");
		Assert.assertEquals("testes\nPula uma linha",dsl.obterValorCampo("elementosForm:sugestoes"));
		
	}
	
	@Test
	public void testeInterageRadioButtons() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
		
	}
	
	@Test
	public void testeInterageCheckBox() {
	
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
		
	}
	
	@Test 
	public void deveInteragirCombo() {
		
	    dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals( "2o grau completo",dsl.obterValorCombo("elementosForm:escolaridade", "2o grau completo"));
		
	}
		
	@Test
	public void deveVerificarValoresCombo() {	
		
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		 
		//verifica uma consulta de nome de opção;
		dsl.obterValorCombo("elementosForm:escolaridade","1o grau incompleto");

	    
		
		}
	@Test 
		public void deveVerificarValoresComboMultiplo(){
		dsl.selecionarCombo("elementosForm:esportes","Futebol");
		dsl.selecionarCombo("elementosForm:esportes","Corrida");
		dsl.selecionarCombo("elementosForm:esportes","Karate");
			
		
			
		}
	
	    
	@Test 
		public void deveInteragirComBotoes() {
			dsl.clicarBotao("buttonSimple");
			String texto=dsl.obterValueElemento("buttonSimple");
			Assert.assertEquals("Obrigado!",texto);
			 
		}
	    
	@Test 
	//@Ignore--> ignotra o teste
	public void deveInteragirComLinks() {
		 dsl.clicarLinks("Voltar");
		 Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextosNaPagina(){
		
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));// verifica se é verdade
		//que dentro do corpo da pagina existe o nome "Campo Treinamento"
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));// verifica se o nome dentro da tag h3 é igual a campo de treinamento
		
		
	}
	 

}
