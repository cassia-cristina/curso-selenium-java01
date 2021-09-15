package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;

public class BasePage {
    protected WebDriver navegador;

    public BasePage(WebDriver navegador){
        this.navegador = navegador;
    }

    public String capturarTextoToast(){
        String texto = navegador.findElement(By.id("toast-container")).getText();
        capturarPrint();
        return texto;
    }

    public void capturarPrint(){
        String screenshotNomeArquivo = String.format("src/test/resources/screenshots/%s.png", Generator.dataHoraParaArquivo());
        Screenshot.tirarPrint(navegador, screenshotNomeArquivo);
    }


}
