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
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;
public class TCEjercicio2 {
    
    private WebDriver driver;
    private WebDriverWait wait;

//Acciones que se van a ejecutar antes de cada test.
@BeforeEach 
    public void inicializarNavegador() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait= new WebDriverWait(driver,Duration.ofSeconds(10));
    }

private void pausa() throws InterruptedException{
Thread.sleep(5000);

}

// Test 1: Validar busqueda exitosa
@Test
    public void envioFormulario() throws InterruptedException{
        //Abrir nuestro navegador
        driver.get("https://teststore.automationtesting.co.uk/index.php");

        //Abrir el buscador
        WebElement buscador=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search our catalog']")));
        buscador.sendKeys("cook");  
        new Actions(driver).sendKeys(buscador, Keys.ENTER).perform();
           
        
       //Validar el titulo

       WebElement titulo=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@id='js-product-list-header']")));           
        Assertions.assertEquals("SEARCH RESULTS",titulo.getText(),"No se muestra pagina resultados!");                
        
    WebElement notificacion=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'There are')]")));           
         if(notificacion.getText().contains("There are"))
        {
            Assertions.assertTrue(true);
        }
        else{
            Assertions.assertTrue(false);
        }
        pausa();  
        List<WebElement> items = driver.findElements(By.xpath("//*[contains(text(),'cook')]"));
        int contador=0;
        if( items.size() > 0){
    for (WebElement item : items) {         
            System.out.println("Texto del encabezado del elemento "+item+" :"+item.getText());
        contador++;
             }           


           }
        

             if(contador> 0){
                Assertions.assertTrue(true);

             }
             else{

                Assertions.assertFalse(true,"Los resultados de búsqueda no coinciden con el item consultado");
             }
            System.out.println("numero de items"+contador);


    }
    
// Acciones que se van a ejecutar despues de cada test.
@AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }

}

