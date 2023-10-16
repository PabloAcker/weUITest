package practica2Parcial.ejercicio1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class CreateUpdateItem {
    ChromeDriver chrome;
    String nombreProyecto = "Nuevo Projecto 1";
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
    public void verifyCreateUpdateNewItemTest() throws InterruptedException {

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

        // Crear un nuevo Item:
        String nombreItem = "Nuevo Item 1";
        // llenar el nombre del item
        chrome.findElement(By.id("NewItemContentInput")).sendKeys(nombreItem);
        // click en el boton add
        chrome.findElement(By.id("NewItemAddButton")).click();
        //verificar si existe el item creado
        Assertions.assertTrue(chrome.findElement(By.xpath("//li[last()]//div[text()='"+nombreItem+"']")).isDisplayed(),
                "ERROR!! el item no fue creado");

        //Update del Item creado:
        String newNameItem = "Update_Nuevo Item 1";
        // click en el item ya creado
        chrome.findElement(By.xpath("//tr[td/div[@class='ItemContentDiv' and text()='"+nombreItem+"']]")).click();
        // limpiar y llenar el nuevo nombre del item
        chrome.findElement(By.id("ItemEditTextbox")).clear();
        chrome.findElement(By.id("ItemEditTextbox")).sendKeys(newNameItem);
        // verificar si el nombre del item se cambio
        Assertions.assertTrue(chrome.findElement(By.xpath("//li[last()]//div[text()='"+newNameItem+"']")).isDisplayed(),
                "ERROR!! el item no fue actualizado");
        Thread.sleep(2000);
    }

    @AfterEach
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        chrome.quit();
    }
}
