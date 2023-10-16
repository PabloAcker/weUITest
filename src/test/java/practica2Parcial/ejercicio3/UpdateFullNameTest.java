package practica2Parcial.ejercicio3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class UpdateFullNameTest {
    ChromeDriver chrome;
    @BeforeEach
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chrome/chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        // implicit
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        chrome.get("http://todo.ly/");
    }
    // ("pablo@pablo.com", "pablo123")
    @Test
    public void editFullName() throws InterruptedException {
        //LOGIN
        // click login button
        chrome.findElement(By.xpath("//img[@src=\"/Images/design/pagelogin.png\"]")).click();
        // set email
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("pablo@pablo.com");
        // set password
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("pablo123");
        // click login
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        // verificar si existe el control del logout
        Assertions.assertTrue((chrome.findElements(By.xpath("//a[text()='Logout']")).size() == 1),
                "ERROR no se pudo ingresar a la sesion");

        //Editar el FullName del perfil
        String newFullName = "PabloAcker";
        // click en el boton de settings
        chrome.findElement(By.xpath("//div/a[@href='javascript:OpenSettingsDialog();']")).click();
        // click en el textbox de FullName y mandar nuevo nombre, limpiando el anterior
        chrome.findElement(By.id("FullNameInput")).clear();
        chrome.findElement(By.id("FullNameInput")).sendKeys(newFullName);
        Thread.sleep(5000);
        // click en el boton de ok
        chrome.findElement(By.xpath("//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")).click();
        //verificacion
        //String expectedResult = chrome.findElement(By.id("FullNameInput")).getText();
        //Assertions.assertEquals(expectedResult, newFullName,"ERROR!! No se realizo el cambio de nombre correctamente");
    }

    @AfterEach
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        chrome.quit();
    }
}
