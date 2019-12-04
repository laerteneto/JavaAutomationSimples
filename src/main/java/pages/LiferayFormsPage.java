package pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import maps.FormsMap;

public class LiferayFormsPage {
	
	public LiferayFormsPage(WebDriver driver) {
		this.driver = driver;
	}

	private WebDriver driver;
	
	
	public void acessarLiferayForms() {
		driver.get("http://forms.liferay.com/web/forms/shared/-/form/372406");
	}

	
	/**
	 * 
	 * Valida o texto party rock.
	 *  
	 */
	public void validarPartyRockTexto() {
		Assert.assertEquals("Let's party rock.", driver.findElement(By.xpath(FormsMap.textoPartyRock())).getText());
	}
	
	
	/**
	 * Preeche formulário Liferay Forms
	 * 
	 * @param nome Texto contendo o nome
	 * @param data Data de nascimento
	 * @param texto Texto que deseja se digitar no campo Porque você ingressou...
	 * 
	 */
	public void preencherFormulario(String nome, String data, String texto) {
		if (!nome.equals(""))
			driver.findElement(By.xpath(FormsMap.campoInput("Qual é seu nome?"))).sendKeys(nome);
		if (!data.equals(""))
			driver.findElement(By.xpath(FormsMap.campoInput("Qual é a data do seu nascimento?"))).sendKeys(data);
		if (!texto.equals(""))
			driver.findElement(By.xpath(FormsMap.campoTextArea("Porque você ingressou na área de testes?"))).sendKeys(texto);
		
		//driver.findElement(By.xpath(FormsMap.botaoPorTexto("Submit"))).click();
		
		// Mesmo vendo com as Expects Conditions se o elemento era clicavel ou visivel, a única solução para o clique foi vis Js 
		WebElement  element= driver.findElement(By.xpath(FormsMap.botaoPorTexto("Submit")));  
		JavascriptExecutor ex=(JavascriptExecutor)driver;
		ex.executeScript("arguments[0].click()", element);
	}
	
	/**
	 * 
	 * Valida a mensagem de sucesso após uma cadastro no formulário
	 *  
	 */
	public void validarMensagemSucesso() {
		Assert.assertEquals("Informações enviadas", driver.findElement(By.xpath(FormsMap.mensagemCadastroSucesso())).getText());
	}
	
	
	public void validarCampoObrigatorio(String labelCampo) {
		WebElement  element = driver.findElement(By.xpath(FormsMap.mensagemCampoObrigatorio(labelCampo)));
		try {
			element.isDisplayed();
		} catch (Exception e) {
			System.out.println("O Elemento não está visivel");
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 * Método criado para garantir que o form não foi enviado, pois encontrei um bug que em muitas vezes o form
	 * era enviado, mesmo ao faltar com o preenchimento de algum(s) campo(s)
	 * 
	 */
	public void validarFormNaoEnviado() {
		try {
			Assert.assertTrue(driver.findElements(By.xpath(FormsMap.mensagemCadastroSucesso())).size()<1);
		 }
		catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
			
		}
	}
	
}
