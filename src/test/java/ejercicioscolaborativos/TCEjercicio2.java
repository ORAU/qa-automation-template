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
import java.lang.String;
public class TCEjercicio2 {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private static String KEY_WORD;

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
        
        KEY_WORD="mug";
        //Abrir el buscador
        WebElement buscador=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search our catalog']")));
        buscador.sendKeys(KEY_WORD);  
        new Actions(driver).sendKeys(buscador, Keys.ENTER).perform();
           
        
       //Validar el titulo

       WebElement titulo=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@id='js-product-list-header']")));           
        Assertions.assertEquals("SEARCH RESULTS",titulo.getText(),"No se muestra pagina resultados!");                
        
    WebElement notificacion=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'There are')]")));           
    Assertions.assertTrue(notificacion.getText().contains("There are"),"No se encontraron productos.");
        pausa();  
        List<WebElement> items = driver.findElements(By.xpath("//*[@class='product-description']/h2"));
        int contador=0;
        if( items.size() > 0)
        {
        for (WebElement item : items) {         
            System.out.println("Texto del encabezado del elemento "+contador+" :"+item.getText());
            if(item.getText().toLowerCase().contains(KEY_WORD))
            {
                contador++;
            }            
            }           
        }     

    //Valida si se encontraron productos relacionados a la búsqueda basado en el conteo de coincidencias
        Assertions.assertTrue(contador> 0,"Los resultados de búsqueda no coinciden con el item consultado");
        System.out.println("numero de items:"+contador);


    }
    
// Acciones que se van a ejecutar despues de cada test.
@AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }

}

