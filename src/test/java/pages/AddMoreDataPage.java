package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddMoreDataPage extends BasePage {

    public AddMoreDataPage(WebDriver navegador) {
        super(navegador);
    }

    public AddMoreDataPage selecionarTipoContato(String tipo){
        WebElement comboType = navegador.findElement(By.id("addmoredata")).findElement(By.name("type"));
        new Select(comboType).selectByVisibleText(tipo);
        return this;
    }

    public AddMoreDataPage digitarContato(String contato){
        navegador.findElement(By.id("addmoredata")).findElement(By.name("contact")).sendKeys(contato);
        return this;
    }

    public MePage clicarSalvar(){
        navegador.findElement(By.id("addmoredata")).findElement(By.linkText("SAVE")).click();
        return new MePage(navegador);
    }

    public MePage adicionarContato(String tipo, String contato){
        selecionarTipoContato(tipo);
        digitarContato(contato);
        return clicarSalvar();
    }

}
