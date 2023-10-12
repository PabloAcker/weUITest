package tareaCreateUpdateProjectTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class CreateProjectTest {
    ChromeDriver chrome;
    String nombreProyecto = "Nuevo Projecto 4";
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
    public void verifyLoginTest(){
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
    public void verifyCreateNewProjectTest(){
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

        // Crear un nuevo proyecto:
        // click Add New Project button
        chrome.findElement(By.xpath("//div[@class=\"AddProjectLiDiv\"]")).click();
        // set NewName Project
        chrome.findElement(By.id("NewProjNameInput")).sendKeys(nombreProyecto);
        // click add button
        chrome.findElement(By.id("NewProjNameButton")).click();

        // verificar si existe el nombre del proyecto nuevo
        List<WebElement> projects = chrome.findElements(By.xpath(String.format("//td[text()='"+nombreProyecto+"']")));
        Assertions.assertFalse(projects.isEmpty(), "ERROR: No existe un proyecto con ese nombre");
    }

    @Test
    public void verifyUpdateProjectTest(){
        String nuevoNombreProyecto = "Update_NuevoProjecto4";
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


        //Actualizar el ultimo proyecto creado:
        // The number of similar projects before edition
        List<WebElement> similarProjectsBefore = chrome.findElements(By.xpath(String.format("//td[text()='"+nuevoNombreProyecto+"']")));

        List<WebElement> similarElements = chrome.findElements(By.xpath(String.format("//tr[td[@class='ProjItemContent' and text()='"+nombreProyecto+"']]")));
        WebElement lastProjectCreated = similarElements.get(similarElements.size() - 1);
        lastProjectCreated.click();
        lastProjectCreated.findElement(By.xpath(".//div[@class='ProjItemMenu']/img")).click();

        chrome.findElement(By.xpath("//ul[@id=\"projectContextMenu\"]/li[@class='edit']")).click();
        chrome.findElement(By.id("ItemEditTextbox")).clear();
        chrome.findElement(By.id("ItemEditTextbox")).sendKeys(nuevoNombreProyecto);
        chrome.findElement(By.id("ItemEditSubmit")).click();

        List<WebElement> updatedProjects = chrome.findElements(By.xpath(String.format("//td[text()='"+nuevoNombreProyecto+"']")));

        //Final Verification
        Assertions.assertFalse(updatedProjects.isEmpty(), "ERROR: El proyecto no fue actualizado correctamente");

    }

    @AfterEach
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        chrome.quit();
    }
}
