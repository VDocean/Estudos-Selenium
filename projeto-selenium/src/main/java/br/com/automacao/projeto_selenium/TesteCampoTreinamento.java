package br.com.automacao.projeto_selenium;
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


public class TesteCampoTreinamento {
	 
 
	
    private WebDriver driver;
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
	public void testeInterageTextField() {
		
	    dsl.escreve("elementosForm:nome","Teste de escrita");
		Assert.assertEquals("Teste de escrita",dsl.obterValorCampo("elementosForm:nome")); // verifica se o valor do campo é igual ao esperad
			
	}
	@Test
	public void testeInterageTextArea() {
		dsl.escreve("elementosForm:sugestoes", "testes\nPula uma linha");
		Assert.assertEquals("testes\nPula uma linha",dsl.obterValorCampo("elementosForm:sugestoes"));
		
	}
	
	@Test
	public void testeInterageRadioButtons() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
	}
	
	@Test
	public void testeInterageCheckBox() {
	
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
		
	}
	
	@Test 
	public void deveInteragirCombo() {
		
	    dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals( "2o grau completo",dsl.obterValorCombo("elementosForm:escolaridade", "2o grau completo"));
		
	}
		
	@Test
	public void deveVerificarValoresCombo() {	
		
		WebElement element=driver.findElement(By.id("elementosForm:escolaridade")); // aqui crio uma varável do tipo WebElement que é o tipo para qualquer retorno de infromação de uma página 
		Select lista=new Select(element);// uso a classe select pois ela tem um conjunto de métodos para manipulação de elementos do tipo select ou lista suspensas em html
		lista.selectByVisibleText("2o grau completo");
		List<WebElement> options=lista.getOptions();
		
		//verifica uma consulta de nome de opção
		boolean encontrou=false;	
		for (WebElement option:options) {
			if (option.getText().equals("1o grau incompleto")) {
				encontrou=true;
				break;
			}
		
		}
		Assert.assertTrue(encontrou);
	    
		
		}
	@Test 
		public void deveVerificarValoresComboMultiplo(){
		dsl.selecionarCombo("elementosForm:esportes","Futebol");
		dsl.selecionarCombo("elementosForm:esportes","Corrida");
		dsl.selecionarCombo("elementosForm:esportes","Karate");
			
		/* 
		//Verifica se a quantidade de opçoes selecionadas é igual a 3
			List<WebElement> AllOptionsSelected=lista.getAllSelectedOptions();
			Assert.assertEquals(3,AllOptionsSelected.size());
			
			//verifica se a quantidade de item selecionados é 2 após desselecionar 1
			
			lista.deselectByVisibleText("Karate");
			List<WebElement> AllOptionsSelectedTwo=lista.getAllSelectedOptions();
			Assert.assertEquals(2,AllOptionsSelectedTwo.size());
		*/
			
		}
	
	    
	@Test 
		public void deveInteragirComBotoes() {
			dsl.clicarBotao("buttonSimple");
			
			WebElement botao=driver.findElement(By.id("buttonSimple"));
			Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
			 
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
