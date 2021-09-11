package tests;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {

    private WebDriver navegador;

    @Before
    public void setup(){
        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver","C:\\webdrivers\\chromedriver\\93\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Navegando para a página Taskit
        navegador.get("http://www.juliodelima.com.br/taskit/");

        //Clicar no link com o texto "Sign In"
        navegador.findElement(By.linkText("Sign in")).click();

        //Identificando o formulário de login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com name "login" que está dentro do formulário de id "signupbox", o texto "cassia0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("cassia0001");

        //Digitar no campo com name "password" que está dentro do formulário de id "signupbox", o texto "123456@"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456@");

        //Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){
        //Clicar no botão através do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //Identificar a popup onde está o formulário de id "addmoredata"
        WebElement formularioAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na combo de name "type" escolher a opção "Phone"
        WebElement comboType = formularioAddMoreData.findElement(By.name("type"));
        new Select(comboType).selectByVisibleText("Phone");

        //No campo de name "contact" digitar "+5562992999999"
        formularioAddMoreData.findElement(By.name("contact")).sendKeys("+5562992999999");

        //Clicar no link de text "SAVE" que está na popup
        formularioAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemId = navegador.findElement(By.id("toast-container"));
        String mensagemSucesso = mensagemId.getText();
        assertEquals("Your contact has been added!",mensagemSucesso);

    }

    @Test
    public void removerUmContatoDeUmUsuario(){
        //Clicar no elemento pelo seu xpath //span[text()="+5562992111110"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+5562992111110\"]/following-sibling::a")).click();

        //Confirmar a janela javascript
        navegador.switchTo().alert().accept();

        //Validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement mensagemId = navegador.findElement(By.id("toast-container"));
        String mensagemSucesso = mensagemId.getText();
        assertEquals("Rest in peace, dear phone!",mensagemSucesso);

        //Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait espera = new WebDriverWait(navegador,10);
        espera.until(ExpectedConditions.stalenessOf(mensagemId));

    }

    @AfterClass
    public static void executarComandos(){
        System.out.println("Testes finalizados com sucesso");
    }

    @After
    public void tearDown(){
        //Fechar o navegador
        //Clicar no link com o texto Logout
        navegador.findElement(By.linkText("Logout")).click();
        navegador.quit();
    }

}