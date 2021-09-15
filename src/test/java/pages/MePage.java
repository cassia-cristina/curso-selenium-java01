package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends BasePage {

    public MePage(WebDriver navegador) {
        super(navegador);
    }

    public MePage clicarAbaMoreDataAboutYou(){
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
        return this;
    }

    public AddMoreDataPage clicarBotaoAddMoreData(){
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
        return new AddMoreDataPage(navegador);
    }

    public MePage localizarContato(String contato){
        String caminho = "//span[text()=\""+contato+"\"]/following-sibling::a";
        navegador.findElement(By.xpath(caminho)).click();
        return this;
    }

    public MePage confirmarExlusao(){
        //Confirmar a janela javascript
        navegador.switchTo().alert().accept();
        return this;
    }

    public MePage removerContato(String contato){
        localizarContato(contato);
        confirmarExlusao();
        return this;
    }

}
