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
	private DSL dsl;
	@BeforeEach // realizado antes da execução de cada método
	public void inicializa() {
	    driver=new ChromeDriver(); //inicializa o driver do navegador em questão
		driver.manage().window().setSize(new Dimension(1200, 765));//seta o tamanho da aba de teste que sera aberta
		driver.get("file:///"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");// diz ao selenim um arquivo será pego
	    dsl=new DSL(driver);
	}
	
	@AfterEach // realizado após a execução de cada método
	public void finaliza() {
		driver.quit();//fecha a guia atual
	}
	
	
	@Test
	//@Ignore
	public void veririficaNome() {
		
		dsl.clicarBotao("elementosForm:cadastrar");
		String msg=dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Nome eh obrigatorio",msg);
	}
	//@Ignore
	@Test
	public void verificaSobrenome() {
		
		dsl.escrever("elementosForm:nome", "Chico");
		dsl.clicarBotao("elementosForm:cadastrar");
		String msg=dsl.alertaObterTextoEAceita();		
		Assert.assertEquals("Sobrenome eh obrigatorio", msg);
		
	
	}
	
	@Test
	public void VerificaSelecaoSexo() {
		
		dsl.escrever("elementosForm:nome", "Chico");
		dsl.escrever("elementosForm:sobrenome", "Bento");
		dsl.clicarBotao("elementosForm:cadastrar");
		String msg=dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Sexo eh obrigatorio", msg);
	
	}
	
	@Test
	public void verificaSelecaoComida() {
		dsl.escrever("elementosForm:nome", "Chico");
		dsl.escrever("elementosForm:sobrenome", "Bento");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarCheck("elementosForm:comidaFavorita:0");
		dsl.clicarCheck("elementosForm:comidaFavorita:3");
		dsl.clicarBotao("elementosForm:cadastrar");
		String msg=dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", msg);
		
	} 
	
	@Test
	public void verificaPraticaEsporte() {
		dsl.escrever("elementosForm:nome","Chico");
		dsl.escrever("elementosForm:sobrenome","Bento");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarCheck("elementosForm:comidaFavorita:0");
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade","Mestrado");
		dsl.selecionarCombo("elementosForm:esportes","Natacao");
		dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		String msg=dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Voce faz esporte ou nao?",msg);
		
		
	}


}
