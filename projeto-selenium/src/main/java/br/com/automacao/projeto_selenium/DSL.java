package br.com.automacao.projeto_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	
	
	public DSL(WebDriver driver) {
		super();
		this.driver = driver;
	}


	public void escreve(String id, String texto) {
		driver.findElement(By.id(id)).sendKeys(texto);
	}
	
	public String obterValorCampo(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	public void clicarRadio(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isRadioMarcado(String id) {
		return driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected();
	}
	
	public void selecionarCombo(String id, String valor) {
		WebElement element=driver.findElement(By.id(id)); // aqui crio uma varável do tipo WebElement que é o tipo para qualquer retorno de infromação de uma página 
		Select lista=new Select(element);// uso a classe select pois ela tem um conjunto de métodos para manipulação de elementos do tipo select ou lista suspensas em html
		
		lista.selectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id,String valor) {
		WebElement element=driver.findElement(By.id(id)); // aqui crio uma varável do tipo WebElement que é o tipo para qualquer retorno de infromação de uma página 
		Select lista=new Select(element);// uso a classe select pois ela tem um conjunto de métodos para manipulação de elementos do tipo select ou lista suspensas em html
		lista.selectByVisibleText(valor);
		return lista.getFirstSelectedOption().getText();
	}
	
	public void clicarBotao(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public void clicarLinks(String link) {
		driver.findElement(By.linkText(link)).click();
	}
	
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	 
}
