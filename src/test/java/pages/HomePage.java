package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{

    public HomePage(WebDriver navegador) {
        super(navegador);
    }

    public MePage clicarMe(){
        WebDriverWait espera = new WebDriverWait(navegador,10);
        WebElement me = navegador.findElement(By.className("me"));
        espera.until(ExpectedConditions.visibilityOf(me)).click();
        return new MePage(navegador);
    }

}
