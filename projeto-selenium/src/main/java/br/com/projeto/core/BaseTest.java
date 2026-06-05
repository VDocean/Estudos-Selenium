package br.com.projeto.core;

import org.junit.jupiter.api.AfterEach;

public class BaseTest {
	
	
	
	 @AfterEach // realizado após a execução de cada método
		public void finaliza() {
			DriverFactory.getDriver().quit();//fecha a guia atual
		}
		
}
