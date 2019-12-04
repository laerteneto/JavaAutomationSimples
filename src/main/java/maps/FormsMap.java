package maps;

public class FormsMap {
	
	
	public static String campoInput(String nomeLabel) {
		return "//label[contains(text(), '" + nomeLabel + "')]/following-sibling::div//input";
	}
	
	public static String campoTextArea(String nomeLabel) {
		return "//label[contains(text(), '" + nomeLabel + "')]/following-sibling::div//textarea";
	}
	
	public static String botaoPorTexto(String nomeBotao) {
		return "//button[contains(text(), '"+nomeBotao+"')]";
	}
	
	public static String mensagemCadastroSucesso() {
		return "//h1[contains(text(), 'Informações enviadas')]";
	}
	
	public static String textoPartyRock() {
		return "//h4[contains(text(), 'party rock')]";
	}
	
	public static String mensagemCampoObrigatorio(String labelCampo) {
		return "//div[@class='form-feedback-item help-block']/..//label[contains(text(),'"+ labelCampo +"')]";
	}
	
}
