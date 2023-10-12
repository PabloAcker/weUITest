package tareaCreateUpdateProjectTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class CreateProjectTest {
    ChromeDriver chrome;
    @BeforeEach
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chrome/chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        // implicit
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        chrome.get("http://todo.ly/");
    }
    // ("pablo@pablo.com", "pablo123")
    @Test
    public void verifyLoginTest() throws InterruptedException {
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
    }
    @Test
    public void verifyCreateProjectTest() throws InterruptedException {
        // click Add New Project button
        chrome.findElement(By.id("AddNewProjDiv")).click();
        // set NewName Project
        chrome.findElement(By.id("NewProjNameInput")).sendKeys("Nuevo Projecto 1");
        // click add button
        chrome.findElement(By.id("NewProjNameButton")).click();

        // verificar si existe el nombre del proyecto nuevo
        //Assertions.assertEquals(chrome.findElements(By.id("NewProjNameButton")),chrome.findElements(By.id("NewProjNameButton")), "ERROR! No existe un proyecto con ese nombre");
    }

    @AfterEach
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        chrome.quit();
    }
}
