package automatizacion;

import java.lang.String;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
    driver.get("https://the-internet.herokuapp.com/login");
    //Otener el titulo del sitio web
    String tituloActual=driver.getTitle();
    String tituloEsperado="The Internet";
    //Validar el titulo del sitio web
    Assertions.assertEquals(tituloEsperado,tituloActual,"El título de la pagina no coincide");
}

//Test 2: Validar un login exitoso
@Test

public void loginExitoso()
{
//Abrir nuestro navegador
driver.get("https://the-internet.herokuapp.com/login");
//Ingresar las credencias de autenticación 
driver.findElement(By.id("username")).sendKeys("tomsmith");
driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
//driver.findElement(By.xpath("*//[@type="submit"]")).click();
driver.findElement(By.xpath("//*[@type='submit']")).click();
//Validación de URL

String urlEsperada= "https://the-internet.herokuapp.com/secure";
Assertions.assertEquals(urlEsperada,driver.getCurrentUrl(),"La url encontrada no coincide");

//Validación de mensaje de Login exitoso

String mensajeEsperado="You logged into a secure area!";
WebElement mensajeLogInExitoso=driver.findElement(By.id("flash"));
String mensajeLogInTexto=mensajeLogInExitoso.getText();

//Uso de condicional para validar el login exitoso

if(mensajeLogInTexto.contains(mensajeEsperado)){
System.out.println("Login Exitoso validado correctamente");
} else {

    System.out.println("Error en el mensaje de login encontrado");
    Assertions.fail("Se esperaba el mensaje: "+mensajeEsperado
    +" \n Pero se encontró: "+mensajeLogInTexto);
}


}
@AfterEach
public void cerrarNavegador(){
  driver.quit();
  System.out.println("Cerrando navegador");

}


}