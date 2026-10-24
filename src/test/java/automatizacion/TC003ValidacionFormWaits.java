package automatizacion;

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
public class TC003ValidacionFormWaits {

//Script para validar el Login Page- Se configura waits
    private WebDriver driver;
    private WebDriverWait wait;


//Acciones que se van a ejecutar antes de cada test.
@BeforeEach 
    public void inicializarNavegador() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Inizialicar wait;

        wait= new WebDriverWait(driver,Duration.ofSeconds(10));
    }

private void pausa() throws InterruptedException{
Thread.sleep(5000);

}


// Test 1: Validar el llenado exitoso del formulario
@Test

public void envioFormulario() throws InterruptedException{
//Abrir nuestro navegador
        driver.get("https://www.automationtesting.co.uk/contactForm.html");
        // Completar los datos del cliente
        // Completar nombre
        WebElement nombre=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='First Name']")));
        nombre.sendKeys("Orlando");
        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("input[placeholder='First Name']"),"value","Orlando"));
        
        // Completar apellido   
        WebElement apellido=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Last Name']")));
        apellido.sendKeys("Martinez");
        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("input[placeholder='Last Name']"),"value","Martinez"));
        
        
        // Completar correo 
         WebElement correo=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Email Address']")));
        correo.sendKeys("orlando.martinez@gmail.com");
        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("input[placeholder='Email Address']"),"value","orlando.martinez@gmail.com"));
        

        WebElement comentarios =wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[placeholder='Comments']")));
        comentarios.sendKeys("Hola");
         pausa();
        WebElement botonSubmit=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='SUBMIT']")));
        botonSubmit.click();

        pausa();
        WebElement mensajeLoginExitoso=
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='col-8 col-12-small'] h3")));
        Assertions.assertEquals(mensajeLoginExitoso.getText(),"Thank you for your mail!","Mensajes no coinciden!");
        
/*

        

        // Completar comentario 
        driver.findElement(By.name("message")).sendKeys("Esta es una práctica de QA Automation,el objetivo es completar el formulario con los datos de un cliente específico, luego dar click al botón Submit y validar el envío exitoso");
        // Click en Submit button 
        driver.findElement(By.xpath("//*[@type='submit']")).click(); 
        // Validación de URL
        String urlEsperada = "https://www.automationtesting.co.uk/contact-form-thank-you.html";
        Assertions.assertEquals(urlEsperada,driver.getCurrentUrl(),"La url encontrada no coincide con la esperada");
        // Validación de mensaje de login exitoso
        WebElement mensajeLogInExitoso = driver.findElement(By.xpath("//*[text()='Thank you for your mail!']"));
        String textoMensaje = mensajeLogInExitoso.getText();
        //Uso de condicional para validar el login exitoso
        if(textoMensaje.contains("Thank you for your mail!")){

            System.out.println("Login Exitoso validdado correctamente");

        } else {
            
            System.out.println("Error en el mensaje de login encontrado");

            Assertions.fail("Se esperaba el mensaje: You logged into a secure area! " 
            + "\n Pero se encontró: " + textoMensaje);
        }
*/        
}

// Acciones que se van a ejecutar despues de cada test.
@AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }
}
