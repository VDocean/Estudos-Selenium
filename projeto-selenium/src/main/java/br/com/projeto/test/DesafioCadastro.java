package br.com.projeto.test;
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

import br.com.projeto.core.BaseTest;
import br.com.projeto.core.DSL;
import br.com.projeto.core.DriverFactory;
import br.com.projeto.page.CampoTreinamentoPage;

public class DesafioCadastro extends BaseTest{
	

	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@BeforeEach // realizado antes da execução de cada método
	public void inicializa() {
		
	    DriverFactory.getDriver();
	   
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
