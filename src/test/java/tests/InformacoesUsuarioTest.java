package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;

import static org.junit.Assert.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.*;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTest.csv")
public class InformacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setup() {
        navegador = Web.createChrome();

        //Identificando o formulário de login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com name "login" que está dentro do formulário de id "signupbox", o texto "cassia0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("cassia0001");

        //Digitar no campo com name "password" que está dentro do formulário de id "signupbox", o texto "123456@"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456@");

        //Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Clicar em um link que possui a class "me"
        WebDriverWait espera = new WebDriverWait(navegador,10);
        WebElement me = navegador.findElement(By.className("me"));
        espera.until(ExpectedConditions.visibilityOf(me)).click();

        //Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name = "tipo") String tipo, @Param(name = "contato") String contato, @Param(name = "mensagem") String mensagemEsperada){
        //Clicar no botão através do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //Identificar a popup onde está o formulário de id "addmoredata"
        WebElement formularioAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na combo de name "type" escolher a opção "Phone" ou "E-mail"
        WebElement comboType = formularioAddMoreData.findElement(By.name("type"));
        new Select(comboType).selectByVisibleText(tipo);

        //No campo de name "contact" informar o número telefone ou e-mail
        formularioAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        //Clicar no link de text "SAVE" que está na popup
        formularioAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemId = navegador.findElement(By.id("toast-container"));
        String mensagemSucesso = mensagemId.getText();
        assertEquals(mensagemEsperada,mensagemSucesso);

        String screenshotNomeArquivo = String.format("src/test/resources/screenshots/%s_%s.png", Generator.dataHoraParaArquivo(), test.getMethodName());
        Screenshot.tirarPrint(navegador, screenshotNomeArquivo);

        //Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait espera = new WebDriverWait(navegador, 10);
        espera.until(ExpectedConditions.stalenessOf(mensagemId));

    }

    @Test
    public void removerUmContatoDeUmUsuario(@Param(name = "contato") String contato, @Param(name = "mensagem") String mensagemEsperada) {
        //Clicar no elemento pelo seu xpath //span[text()="contato"]/following-sibling::a
        String caminho = "//span[text()=\""+contato+"\"]/following-sibling::a";
         navegador.findElement(By.xpath(caminho)).click();

        //Confirmar a janela javascript
        navegador.switchTo().alert().accept();

        //Validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement mensagemId = navegador.findElement(By.id("toast-container"));
        String mensagemSucesso = mensagemId.getText();
        assertEquals(mensagemEsperada, mensagemSucesso);

        String screenshotNomeArquivo = String.format("src/test/resources/screenshots/%s_%s.png", Generator.dataHoraParaArquivo(), test.getMethodName());
        Screenshot.tirarPrint(navegador, screenshotNomeArquivo);

        //Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait espera = new WebDriverWait(navegador, 10);
        espera.until(ExpectedConditions.stalenessOf(mensagemId));

    }

    @After
    public void tearDown() {
        navegador.findElement(By.linkText("Logout")).click();
        navegador.quit();
    }

}