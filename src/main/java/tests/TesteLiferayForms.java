package tests;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LiferayFormsPage;

public class TesteLiferayForms {
	
	private WebDriver driver;
	private LiferayFormsPage liferayFormsPage;

	@Before
	public void inicializa(){
		//Web driver setup
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
		// Window setup
		driver.manage().window().maximize();
		driver.get("http://forms.liferay.com/web/forms/shared/-/form/372406");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Liferay page set up
		liferayFormsPage = new LiferayFormsPage(driver);
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}
	
	
	@Test
	public void validarTextoPartyRocks(){
		liferayFormsPage.validarPartyRockTexto();
	}
	
	@Test
	public void preencherFormularioCompleto(){
		liferayFormsPage.preencherFormulario("Laerte de Mesquita", "01/01/1992", "Blablabla");
		liferayFormsPage.validarMensagemSucesso();
	}
	
	
	
	
	
	
	
	
	
	@Test
	public void deveIntegarirComRadioButton(){
		liferayFormsPage.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(liferayFormsPage.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void deveIntegarirComCheckbox(){
		liferayFormsPage.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(liferayFormsPage.isCheckMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void deveIntegarirComCombo(){
		liferayFormsPage.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", liferayFormsPage.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo(){
		Assert.assertEquals(8, liferayFormsPage.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(liferayFormsPage.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo(){
		liferayFormsPage.selecionarCombo("elementosForm:esportes", "Natacao");
		liferayFormsPage.selecionarCombo("elementosForm:esportes", "Corrida");
		liferayFormsPage.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = liferayFormsPage.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		liferayFormsPage.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = liferayFormsPage.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
	
	@Test
	public void deveinteragirComBotoes(){
		liferayFormsPage.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", liferayFormsPage.obterValueElemento("buttonSimple"));
	}
	
	@Test
	public void deveinteragirComLinks(){
		liferayFormsPage.clicarLink("Voltar");
		
		Assert.assertEquals("Voltou!", liferayFormsPage.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextosNaPagina(){
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", liferayFormsPage.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", 
				liferayFormsPage.obterTexto(By.className("facilAchar")));
	}
	
	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border=arguments[1]", element, "solid 4px red");
		
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		liferayFormsPage.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
	
}


