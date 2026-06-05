package br.com.projeto.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.projeto.test.DesafioCadastro;
import br.com.projeto.test.DesafioValidaRegrasDeNegocios;
import br.com.projeto.test.TestaAlert;

@RunWith(Suite.class)
@SuiteClasses({
	DesafioValidaRegrasDeNegocios.class,
	TestaAlert.class,
	DesafioCadastro.class
	
	})

public class SuiteTeste {
	

}
