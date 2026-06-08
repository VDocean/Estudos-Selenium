package br.com.projeto.core;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

public class BaseTest {
	
	
	 @Rule
	 public TestName testName=new TestName();
	 
	 @AfterEach // realizado após a execução de cada método
		public void finaliza() throws IOException {
		 
		 TakesScreenshot ss=(TakesScreenshot) DriverFactory.getDriver();
		 File arquivo=ss.getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(arquivo,new File("target" + File.separator + "screenshot/" + testName.getMethodName()));
		 
		 if(Propriedades.FECHAR_O_BROWNSER) {
			DriverFactory.killDriver();//fecha a guia atual
		 }	
		 }
		
}
