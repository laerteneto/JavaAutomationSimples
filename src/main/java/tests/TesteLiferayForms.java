package tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LiferayFormsPage;

public class TesteLiferayForms {

	private WebDriver driver;
	private LiferayFormsPage liferayFormsPage;

	@Before
	public void inicializa() {
		// Web driver setup
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();

		// Window setup
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.get("http://forms.liferay.com/web/forms/shared/-/form/372406");

		// Liferay page set up
		liferayFormsPage = new LiferayFormsPage(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void validarTextoPartyRocks() {
		liferayFormsPage.acessarLiferayForms();
		liferayFormsPage.validarPartyRockTexto();
	}

	@Test
	public void preencherFormularioCompleto() {
		liferayFormsPage.acessarLiferayForms();
		liferayFormsPage.preencherFormulario("Laerte Mello", "01/01/1992", "Blablabla");
		liferayFormsPage.validarMensagemSucesso();
	}

	@Test
	public void obrigatoriedadeCamposDataNascimentoEPorqueVoceIngressou() {
		liferayFormsPage.acessarLiferayForms();
		liferayFormsPage.preencherFormulario("Laerte Mello", "", "");
		liferayFormsPage.validarCampoObrigatorio("Qual é a data do seu nascimento?");
		liferayFormsPage.validarCampoObrigatorio("Porque você ingressou na área de testes?");
		liferayFormsPage.validarFormNaoEnviado();
	}

	@Test
	public void obrigatoriedadeCamposNomeEPorqueVoceIngressou() {
		liferayFormsPage.acessarLiferayForms();
		liferayFormsPage.preencherFormulario("", "01/01/1992", "");
		liferayFormsPage.validarCampoObrigatorio("Qual é seu nome?");
		liferayFormsPage.validarCampoObrigatorio("Porque você ingressou na área de testes?");
		liferayFormsPage.validarFormNaoEnviado();
	}

	@Test
	public void obrigatoriedadeCamposNomeEDataNascimento() {
		liferayFormsPage.acessarLiferayForms();
		liferayFormsPage.preencherFormulario("", "", "Blablabla");
		liferayFormsPage.validarCampoObrigatorio("Qual é seu nome?");
		liferayFormsPage.validarCampoObrigatorio("Qual é a data do seu nascimento?");
		liferayFormsPage.validarFormNaoEnviado();
	}

}
