package br.com.automacao.projeto_selenium;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/* PARAMETRIZAÇÃO */
/* É um conceito que trata o conteúdo que são postos dentro dos campos de uma page, de forma que possa fazer inúmeros testes de uma vez só*/
@RunWith(Parameterized.class)
public class TesteRegrasCadastro {
	
    private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	@Parameter //------> Aqui digo a ordem que deve ser seguida pelos parâmetros no método no qual eles serão passados
	private String nome;
	@Parameter(value=1)
	private String sobrenome;
	@Parameter(value=2)
	private String sexo;
	@Parameter(value=3)
	private List<String> comidas;
	@Parameter(value=4)
	private String[] esportes;
	@Parameter(value=5)
	private String msg;
	
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
	
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
			//{"","","", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			//{"Wagner","","", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			//{"Wagner", "Costa", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Wagner", "Costa", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Wagner", "Costa", "Masculino", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	
	
	
	@Test
	public void verificaPraticaEsporte() {
		
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		}else if (sexo.equals("Femenino")) {
			page.setSexoFemenino();
		}
		
		if(comidas.contains("Carne")) page.setComidaCarne();
		if(comidas.contains("Pizza")) page.setComidaPizza();
		if(comidas.contains("Vegetariano")) page.setComidaVegetariano();
		
		
		page.setEsporte(esportes);
		
		page.cadastrar();
	    System.out.println(nome);
		Assert.assertEquals(msg,dsl.alertaObterTextoEAceita());
		
		
	}

}
