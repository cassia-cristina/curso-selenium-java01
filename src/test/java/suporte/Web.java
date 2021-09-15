package suporte;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Web {
    public static final String USERNAME = "teste";
    public static final String AUTOMATE_KEY = "teste";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver createChrome(){
        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver\\93\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Navegando para a página Taskit
        navegador.get("http://www.juliodelima.com.br/taskit/");

        return navegador;
    }

    public static void fazerLogout(WebDriver navegador){
        navegador.findElement(By.linkText("Logout")).click();
    }

    //metodo para executar os testes na nuvem usando a plataforma Browserstack
    public static WebDriver createBrowserStack(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "chrome");
        caps.setCapability("browser_version", "93.0");

        WebDriver navegador = null;
        try {
            navegador = new RemoteWebDriver(new URL(URL), caps);
            navegador.manage().window().maximize();
            navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            // Navegando para a página do Taskit
            navegador.get("http://www.juliodelima.com.br/taskit");
        } catch (MalformedURLException e) {
            System.out.print("Houveram problemas com a URL: " + e.getMessage());
        }

        return navegador;
    }
}
