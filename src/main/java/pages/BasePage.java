package pages;
import org.openqa.selenium.WebDriver;

public class BasePage {
	
	private WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void acessarPagina(String link) {
		driver.get("http://forms.liferay.com/web/forms/shared/-/form/372406");
	}
	
	

}
