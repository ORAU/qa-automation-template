package automatizacion;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC003ValidacionForm {
//Script para validar el Login Page
    WebDriver driver;

//Acciones que se van a ejecutar antes de cada test.
@BeforeEach 
    public void inicializarNavegador() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


// Test 1: Validar el llenado exitoso del formulario
@Test
    public void envioFormulario(){
        //Abrir nuestro navegador
        driver.get("https://www.automationtesting.co.uk/contactForm.html");
        // Ingresar los datos del cliente
        driver.findElement(By.name("first_name")).sendKeys("Orlando");       
        driver.findElement(By.name("last_name")).sendKeys("Martínez");
        driver.findElement(By.name("email")).sendKeys("orlando.martinez@bancocuscatlan.com");
        driver.findElement(By.name("message")).sendKeys("Esta es una práctica de QA Automation,el objetivo es completar el formulario con los datos de un cliente específico, luego dar click al botón Submit y validar el envío exitoso");
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

    }
    
// Acciones que se van a ejecutar despues de cada test.
@AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }

}