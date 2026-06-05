package br.com.projeto.core;


import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
     /**********Text field e text area***************/
	
	//permite escrever em campo acessado por tag
	public void escrever(By by, String texto) {
		DriverFactory.getDriver().findElement(by).clear();
		DriverFactory.getDriver().findElement(by).sendKeys(texto);
	}
	
	//permite escrever em campo acessado por id
	public void escrever(String id_campo,String texto) {
		escrever(By.id(id_campo),texto);
	}
	
	
	public String obterValorCampo(String id) {
		return DriverFactory.getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	/**************************Radio e Check*****************/
	public void clicarRadio(By by) {
		DriverFactory.getDriver().findElement(by).click();
	}
	
	public void clicarRadio(String id) {
		clicarRadio(By.id(id));
	}
	
	public boolean isRadioMarcado(String id) {
		return DriverFactory.getDriver().findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheck(String id) {
		DriverFactory.getDriver().findElement(By.id(id)).click();
		
	}
	
	public boolean isCheckMarcado(String id) {
		return DriverFactory.getDriver().findElement(By.id(id)).isSelected();
	}
	
	public boolean isCheckMarcado(By by) {
		return DriverFactory.getDriver().findElement(by).isSelected();
	}
	
	
	/**********Combo*************************/
	
	
	public void selecionarCombo(String id, String valor) {
		WebElement element=DriverFactory.getDriver().findElement(By.id(id)); // aqui crio uma varável do tipo WebElement que é o tipo para qualquer retorno de infromação de uma página 
		Select combo=new Select(element);// uso a classe select pois ela tem um conjunto de métodos para manipulação de elementos do tipo select ou lista suspensas em html
		combo.selectByVisibleText(valor);
	}
	

     public void selecionarComboPrime(String path,String textoVisivel) {
    	 DriverFactory.getDriver().findElement(By.xpath(path)).click();
    	 DriverFactory.getDriver().findElement(By.xpath("//li[contains(normalize-space(.),'" + textoVisivel + "')]")).click();
    
    }

	
	public String obterValorCombo(String id,String valor) {
		WebElement element=DriverFactory.getDriver().findElement(By.id(id)); // aqui crio uma varável do tipo WebElement que é o tipo para qualquer retorno de infromação de uma página 
		Select lista=new Select(element);// uso a classe select pois ela tem um conjunto de métodos para manipulação de elementos do tipo select ou lista suspensas em html
		lista.selectByVisibleText(valor);
		return lista.getFirstSelectedOption().getText();
	}
	
	public void deselecionarCombo(String id, String valor) {
		WebElement element=DriverFactory.getDriver().findElement(By.id(id));
		Select combo=new Select(element);
		 combo.deselectByVisibleText(valor);
	}
	
	public List<String> obterValorCombo(String id) {
		WebElement element=DriverFactory.getDriver().findElement(By.id(id));
		Select combo=new Select(element);
		List<WebElement> allSelectedOptions=combo.getAllSelectedOptions();
		List<String> valores=new ArrayList<String>();
		
		for (WebElement opcao:allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id) {
		WebElement element=DriverFactory.getDriver().findElement(By.id(id));
		Select combo=new Select(element);
		List<WebElement> options=combo.getOptions();
		return options.size();
	}
	
	public boolean verificarOpcaoCombo(String id, String opcao) {
		WebElement element=DriverFactory.getDriver().findElement(By.id(id));
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
		DriverFactory.getDriver().findElement(By.id(id)).click();
	}
	
	public String obterValueElemento(String id) {
		return DriverFactory.getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	
	/****************Link*********************/
	
	public void clicarLinks(String link) {
		DriverFactory.getDriver().findElement(By.linkText(link)).click();
	}
	
	/**********************Textos***********************/
	
	public String obterTexto(By by) {
		return DriverFactory.getDriver().findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	/****************Alerts************************/
	
	public String alertaObterTexto() {
		Alert alert=DriverFactory.getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita() {
		Alert alert=DriverFactory.getDriver().switchTo().alert();
		String valor=alert.getText();
		alert.accept();
		return valor;
	}
	
	public String alertaObterTextoENega() {
		Alert alert=DriverFactory.getDriver().switchTo().alert();
		String valor=alert.getText();
		alert.dismiss();
		return valor;
	}
	
	public void alertaEscrever(String valor) {
		Alert alert=DriverFactory.getDriver().switchTo().alert();
		alert.sendKeys(valor);
	}
	
	public void AlertSomenteAceita() {
		Alert alert=DriverFactory.getDriver().switchTo().alert();
		alert.accept();
	}
	
	
	/**************** Frames e Janelas *****************/
	
	public void entrarFrame(String id) {
		DriverFactory.getDriver().switchTo().frame(id);
	}
	
	public void sairFrame() {
		DriverFactory.getDriver().switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		DriverFactory.getDriver().switchTo().window(id);
	}
	 
	 
}
