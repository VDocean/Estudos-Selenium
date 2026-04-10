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
	private CampoTreinamentoPage page;
	
	@BeforeEach // realizado antes da execução de cada método
	public void inicializa() {
		driver=new ChromeDriver(); 
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html"); // essa linha pede para o driver buscar uma url
		// file/// indica que é uma url local , System.getProperty("user.dir") retorna o diretorio de trabalho atual
	    dsl=new DSL(driver);
	    page=new CampoTreinamentoPage(driver);
	}
	
	
	@AfterEach // realizado após a execução de cada método
	public void finaliza() {
		driver.quit();//fecha o navegador
	}
	
	@Test
	public void preencheDadosCadastro() {
		
		page.setNome("Chico");
		page.setSobrenome("Bento");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEscolaridade("2o grau completo");
		page.setEsporte("Corrida");
		page.cadastrar();
		 		
		//Validações
		
		Assert.assertEquals("Nome: Chico",page.obterNomeCadastro());
		Assert.assertEquals("Sobrenome: Bento",page.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Masculino",page.obterSexoCadastro());
		Assert.assertEquals("Escolaridade: 2graucomp",page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Corrida",page.obterEsportesCadastro());
		
	}
	

}
