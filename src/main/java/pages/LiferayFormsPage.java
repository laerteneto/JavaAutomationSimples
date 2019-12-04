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
	 * Preeche formul�rio Liferay Forms
	 * 
	 * @param nome Texto contendo o nome
	 * @param data Data de nascimento
	 * @param texto Texto que deseja se digitar no campo Porque voc� ingressou...
	 * 
	 */
	public void preencherFormulario(String nome, String data, String texto) {
		if (!nome.equals(""))
			driver.findElement(By.xpath(FormsMap.campoInput("Qual � seu nome?"))).sendKeys(nome);
		if (!data.equals(""))
			driver.findElement(By.xpath(FormsMap.campoInput("Qual � a data do seu nascimento?"))).sendKeys(data);
		if (!texto.equals(""))
			driver.findElement(By.xpath(FormsMap.campoTextArea("Porque voc� ingressou na �rea de testes?"))).sendKeys(texto);
		
		//driver.findElement(By.xpath(FormsMap.botaoPorTexto("Submit"))).click();
		
		// Mesmo vendo com as Expects Conditions se o elemento era clicavel ou visivel, a �nica solu��o para o clique foi vis Js 
		WebElement  element= driver.findElement(By.xpath(FormsMap.botaoPorTexto("Submit")));  
		JavascriptExecutor ex=(JavascriptExecutor)driver;
		ex.executeScript("arguments[0].click()", element);
	}
	
	/**
	 * 
	 * Valida a mensagem de sucesso ap�s uma cadastro no formul�rio
	 *  
	 */
	public void validarMensagemSucesso() {
		Assert.assertEquals("Informa��es enviadas", driver.findElement(By.xpath(FormsMap.mensagemCadastroSucesso())).getText());
	}
	
	
	public void validarCampoObrigatorio(String labelCampo) {
		WebElement  element = driver.findElement(By.xpath(FormsMap.mensagemCampoObrigatorio(labelCampo)));
		try {
			element.isDisplayed();
		} catch (Exception e) {
			System.out.println("O Elemento n�o est� visivel");
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 * M�todo criado para garantir que o form n�o foi enviado, pois encontrei um bug que em muitas vezes o form
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
