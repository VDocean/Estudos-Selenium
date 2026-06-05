package br.com.projeto.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// O objetivo desta classe eh evitar chamadas repetitivas do friver
public class DriverFactory {
	
	static WebDriver driver;
	
	private DriverFactory() {
		
	}
	
	public static WebDriver getDriver() {
		if(driver==null) {
	    driver=new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		}
		return driver;
	}
	
	public static void killDriver() {
		driver.quit();
	}

}
