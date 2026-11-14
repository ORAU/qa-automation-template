package examenfinal;

import org.junit.jupiter.api.AfterEach;
import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.lang.InterruptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
public class EX001 {
    
    private WebDriver driver;
    private WebDriverWait wait;
   
//Acciones que se van a ejecutar antes de cada escenario de prueba.
@BeforeEach 
    public void inicializarNavegador() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait= new WebDriverWait(driver,Duration.ofSeconds(10));
    }


private void pausa() throws InterruptedException{
Thread.sleep(2000);

}
// Test 1- RF-001: Compra de productos con usuario autenticado
@Test
    public void compraProductoUserAutenticado() throws InterruptedException{
        //Abrir navegador e ingresar a pantalla login
        driver.get("https://teststore.automationtesting.co.uk/index.php");
        WebElement botonLogin=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='hidden-sm-down' and contains(text(),'Sign in')]")));           
        botonLogin.click();
        
        //Ingresar credenciales
        String email="orlan_d_c@hotmail.com";
        String pwd="Password12345678*";
        WebElement campoEmail=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("field-email")));           
        campoEmail.sendKeys(email);        
        WebElement campoPwd=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("field-password")));           
        campoPwd.sendKeys(pwd);        
        WebElement botonSignIn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit-login")));           
        botonSignIn.click();
        //Validar login Exitoso:
        WebElement botonSignOut=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='logout hidden-sm-down']")));           
        WebElement botonAccount=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='account']")));           
        Assertions.assertTrue((botonSignOut.isDisplayed() && botonAccount.isDisplayed()),"El usuario no ha iniciado sesión correctamente.");
        
    }
    
// Acciones que se van a ejecutar despues de cada test.
@AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }

}

