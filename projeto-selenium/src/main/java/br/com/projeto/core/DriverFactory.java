package br.com.projeto.core;
import br.com.projeto.core.Propriedades;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// O objetivo desta classe eh evitar chamadas repetitivas do friver
public class DriverFactory {
	
	static WebDriver driver;
	
	private DriverFactory() {
		
	}
	
	public static WebDriver getDriver() {
		if(driver==null) {
		switch(Propriedades.brownser) {
		case FIREFOX: driver=new FirefoxDriver(); break;
		case CHROME:driver=new ChromeDriver();break;
		}
			
	    
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		
		}
		return driver;
	}
	
	public static void killDriver() {
		if(driver!=null) {
		driver.quit();
		driver=null;
		}
	}

}
