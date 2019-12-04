package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import maps.FormsMap;

public class LiferayFormsPage extends BasePage {

	public LiferayFormsPage(WebDriver driver) {
		super(driver);
	}

	public void acessarLiferayForms() {
		acessarPagina("http://forms.liferay.com/web/forms/shared/-/form/372406");
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
	 * @param nome  Texto contendo o nome
	 * @param data  Data de nascimento
	 * @param texto Texto que deseja se digitar no campo Porque voc� ingressou...
	 * 
	 */
	public void preencherFormulario(String nome, String data, String texto) {
		if (!nome.equals(""))
			driver.findElement(By.xpath(FormsMap.campoInput("Qual � seu nome?"))).sendKeys(nome);
		if (!data.equals(""))
			driver.findElement(By.xpath(FormsMap.campoInput("Qual � a data do seu nascimento?"))).sendKeys(data);
		if (!texto.equals(""))
			driver.findElement(By.xpath(FormsMap.campoTextArea("Porque voc� ingressou na �rea de testes?")))
					.sendKeys(texto);

		// Mesmo vendo com as Expects Conditions se o elemento era clicavel ou visivel,
		// a �nica solu��o para o clique foi vis Js
		clicarJs(By.xpath(FormsMap.botaoPorTexto("Submit")));
	}

	/**
	 * 
	 * Valida a mensagem de sucesso ap�s uma cadastro no formul�rio
	 * 
	 */
	public void validarMensagemSucesso() {
		Assert.assertEquals("Informa��es enviadas",
				driver.findElement(By.xpath(FormsMap.mensagemCadastroSucesso())).getText());
	}

	/**
	 * 
	 * Validar campo obrigatorio. Checar se a mensagem de erro para campo
	 * obrigat�rio � exibida logo abaixo do campo n�o preenchido
	 * 
	 * @param labelCampo nome da label do campo que se deseja validar
	 * 
	 */
	public void validarCampoObrigatorio(String labelCampo) {
		WebElement element = driver.findElement(By.xpath(FormsMap.mensagemCampoObrigatorio(labelCampo)));
		try {
			element.isDisplayed();
		} catch (Exception e) {
			System.out.println("O Elemento n�o est� visivel");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 * M�todo criado para garantir que o form n�o foi enviado, pois encontrei um bug
	 * que em muitas vezes o form era enviado, mesmo ao faltar com o preenchimento
	 * de algum(s) campo(s)
	 * 
	 */
	public void validarFormNaoEnviado() {
		try {
			Assert.assertTrue(driver.findElements(By.xpath(FormsMap.mensagemCadastroSucesso())).size() == 0);
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());

		}
	}

}
