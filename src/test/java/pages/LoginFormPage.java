package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends  BasePage {

    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage inserirLogin(String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
        return this;
    }

    public LoginFormPage inserirPassword (String password){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);
        return this;
    }

    public HomePage clicarBotaoSignIn(){
        navegador.findElement(By.linkText("SIGN IN")).click();
        return new HomePage(navegador);
    }

    public HomePage realizarLogin(String login, String password){
        inserirLogin(login);
        inserirPassword(password);
        return clicarBotaoSignIn();
    }



}
