package br.com.automacao.projeto_selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
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

     /**********Text field e text area***************/
	
	//permite escrever em campo acessado por tag
	public void escrever(By by, String texto) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}
	
	//permite escrever em campo acessado por id
	public void escrever(String id_campo,String texto) {
		escrever(By.id(id_campo),texto);
	}
	
	
	public String obterValorCampo(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	/**************************Radio e Check*****************/
	
	
	public void clicarRadio(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isRadioMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheck(String id) {
		driver.findElement(By.id(id)).click();
		
	}
	
	public boolean isCheckMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	
	/**********Combo*************************/
	
	
	public void selecionarCombo(String id, String valor) {
		WebElement element=driver.findElement(By.id(id)); // aqui crio uma varável do tipo WebElement que é o tipo para qualquer retorno de infromação de uma página 
		Select combo=new Select(element);// uso a classe select pois ela tem um conjunto de métodos para manipulação de elementos do tipo select ou lista suspensas em html
		combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id,String valor) {
		WebElement element=driver.findElement(By.id(id)); // aqui crio uma varável do tipo WebElement que é o tipo para qualquer retorno de infromação de uma página 
		Select lista=new Select(element);// uso a classe select pois ela tem um conjunto de métodos para manipulação de elementos do tipo select ou lista suspensas em html
		lista.selectByVisibleText(valor);
		return lista.getFirstSelectedOption().getText();
	}
	
	public void deselecionarCombo(String id, String valor) {
		WebElement element=driver.findElement(By.id(id));
		Select combo=new Select(element);
		 combo.deselectByVisibleText(valor);
	}
	
	public List<String> obterValorCombo(String id) {
		WebElement element=driver.findElement(By.id(id));
		Select combo=new Select(element);
		List<WebElement> allSelectedOptions=combo.getAllSelectedOptions();
		List<String> valores=new ArrayList<String>();
		
		for (WebElement opcao:allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id) {
		WebElement element=driver.findElement(By.id(id));
		Select combo=new Select(element);
		List<WebElement> options=combo.getOptions();
		return options.size();
	}
	
	public boolean verificarOpcaoCombo(String id, String opcao) {
		WebElement element=driver.findElement(By.id(id));
		Select combo=new Select(element);
		List<WebElement> options=combo.getOptions();
		
		for(WebElement option:options) {
			if(option.equals(opcao)) {
				return true;
			}
		}
		return false;
		
	}
	
	
	/********************Botao****************************/
	
	public void clicarBotao(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public String obterValueElemento(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	
	/****************Link*********************/
	
	public void clicarLinks(String link) {
		driver.findElement(By.linkText(link)).click();
	}
	
	/**********************Textos***********************/
	
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	/****************Alerts************************/
	
	public String alertaObterTexto() {
		Alert alert=driver.switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita() {
		Alert alert=driver.switchTo().alert();
		String valor=alert.getText();
		alert.accept();
		return valor;
	}
	
	public String alertaObterTextoENega() {
		Alert alert=driver.switchTo().alert();
		String valor=alert.getText();
		alert.dismiss();
		return valor;
	}
	
	public void alertaEscrever(String valor) {
		Alert alert=driver.switchTo().alert();
		alert.sendKeys(valor);
	}
	
	public void AlertSomenteAceita() {
		Alert alert=driver.switchTo().alert();
		alert.accept();
	}
	
	
	/**************** Frames e Janelas *****************/
	
	public void entrarFrame(String id) {
		driver.switchTo().frame(id);
	}
	
	public void sairFrame() {
		driver.switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		driver.switchTo().window(id);
	}
}
