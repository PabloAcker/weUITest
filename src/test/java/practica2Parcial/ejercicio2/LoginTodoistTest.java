package practica2Parcial.ejercicio2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginTodoistTest {
    ChromeDriver chrome;
    @BeforeEach
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chrome/chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        // implicit
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        chrome.get("https://todoist.com/app/");
    }
    // ("pablo@pablo.com", "P4bl0123")
    @Test
    public void verifyLoginTest() throws InterruptedException {
        // set email
        chrome.findElement(By.id("element-0")).click();
        chrome.findElement(By.id("element-0")).sendKeys("pablo@pablo.com");
        // set password
        chrome.findElement(By.id("element-3")).click();
        chrome.findElement(By.id("element-3")).sendKeys("P4bl0123");
        // click login button
        chrome.findElement(By.xpath("//button[@class='F9gvIPl rWfXb_e _8313bd46 _7a4dbd5f _95951888 _2a3b75a1 _8c75067a']")).click();
        // verificacion de que exista el boton de perfil para desloguearse
        Assertions.assertNotNull(chrome.findElement(By.id(":r3:")), "ERROR: No se pudo iniciar secion");

        Thread.sleep(5000);
    }

    @AfterEach
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        chrome.quit();
    }
}
