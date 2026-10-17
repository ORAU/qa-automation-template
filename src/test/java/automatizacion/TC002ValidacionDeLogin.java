package automatizacion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC002ValidacionDeLogin{
//Script para validar el Login Page
private WebDriver driver;

@BeforeEach
public void inicializarNavegador (){

    driver= new ChromeDriver();
    driver.manage().window().maximize();

}
//Test 1: Validar el titulo de la pagina antes de realizar el login
@Test
public void validarTituloDeLaPagina(){
    //Abrir nuestro navegador
    driver.get("https://practicetestautomation.com/practice-test-login/");
    //Otener el titulo del sitio web
    String tituloActual=driver.getTitle();
    String tituloEsperado="Test Login | Practice Test Automation";
    //Validar el titulo del sitio web
    Assertions.assertEquals(tituloEsperado,tituloActual,"El título de la pagina no coincide");
}

//Test 2: Validar un login exitoso
@Test

public void loginExitoso()
{
//Abrir nuestro navegador
driver.get("https://practicetestautomation.com/practice-test-login/");
//Ingresar las credencias de autenticación 
driver.findElement(By.id("username")).sendKeys("student");
driver.findElement(By.id("password")).sendKeys("Password123");
driver.findElement(By.id("submit")).click();


}
@AfterEach
public void cerrarNavegador(){
  driver.quit();
  System.out.println("Cerrando navegador");

}


}