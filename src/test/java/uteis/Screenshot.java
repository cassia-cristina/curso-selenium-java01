package uteis;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Screenshot {
    public static void tirarPrint(WebDriver navegador, String arquivo){
        File screenshot = ((TakesScreenshot)navegador).getScreenshotAs(OutputType.FILE);

        try{
            Files.copy(new FileInputStream(screenshot), Path.of(arquivo));

        } catch (Exception e){
            System.out.println("Ocorreu um problema ao copiar o arquivo para a pasta: " + e.getMessage());
        }
    }

}
