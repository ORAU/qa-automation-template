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
public class TCEjercicio23 {
    
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

// Test 3: Validar navegación por categoría
@Test
    public void envioFormulario() throws InterruptedException{
        //Abrir nuestro navegador
        driver.get("https://teststore.automationtesting.co.uk/index.php");
        driver.findElement(By.linkText("ART")).click(); 
        String expectedUrl="https://teststore.automationtesting.co.uk/index.php?id_category=9&controller=category";
        pausa();  
        //Validar la url de la página
        Assertions.assertEquals(expectedUrl,driver.getCurrentUrl(),"La Url mostrada en pantalla no coincide con la URL esperada de la página");

       //Validar el titulo de la página

       WebElement titulo=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='text-uppercase h6' and contains(text(),'Art')]")));           
        Assertions.assertEquals("Art".toLowerCase(),titulo.getText().toLowerCase(),"No se muestra la página de la categoría seleccionada");                
        
        //Identifica la cantidad de articulos de arte arte mostrados en la página
    WebElement notificacion=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'There are')]")));           
    Assertions.assertTrue(notificacion.getText().contains("There are"),"No se encontraron productos en la búsqueda de categoría seleccionada.");
    pausa();  
    By listadoProductos = By.xpath("//*[@class='h3 product-title']/a");
    List<WebElement> articulosListados = wait.until(
    ExpectedConditions.visibilityOfAllElementsLocatedBy(listadoProductos));
    WebElement ultimoItemListado=null;
    String ultimoNombreListado=null;
    int contador=0;
        if( articulosListados.size() > 0)
        {
            for(WebElement articulo: articulosListados){
                contador++;
               System.out.println("El nombre del producto: "+contador+" encontrado es :"+articulo.getText());
                ultimoItemListado=articulo;
                ultimoNombreListado=articulo.getText();
            } 
        }     

    //Valida si se encontraron productos relacionados a la categoría seleccionada
        Assertions.assertTrue(contador> 0,"Los resultados de búsqueda no coinciden con el item consultado");
        System.out.println("El número de items encontrados fueron:"+contador);

    if(ultimoItemListado!= null){
        ultimoItemListado.click();
        pausa();
        //String byLocator="//span[contains(text(),"+"\'"+ultimoNombreListado+"\'"+")]";
        String byLocator="//h1[@class='h1']";
        By byTituloItem=By.xpath(byLocator);
        WebElement itemConsultado=wait.until(ExpectedConditions.visibilityOfElementLocated(byTituloItem));
        Assertions.assertTrue(itemConsultado.getText().toUpperCase().contains(ultimoNombreListado.toUpperCase()),"No se encontraró el título del producto seleccionado.");
        System.out.println("El item seleccionado fue:"+itemConsultado.getText());
       
        //Validar el precio del producto seleccionado sea visible
        String byLocatorPrecio="//span[@class='current-price-value']";
        By byPriceItem=By.xpath(byLocatorPrecio);
        WebElement itemPrecioConsultado=wait.until(ExpectedConditions.visibilityOfElementLocated(byPriceItem));
        String itemPrecioCadena=itemPrecioConsultado.getText();
        System.out.println("El precio de item encontrados es: "+itemPrecioCadena);
        Assertions.assertTrue((itemPrecioCadena != null && !itemPrecioCadena.isBlank()),"No se encontró el precio del producto seleccionado.");

    }

    }
    
// Acciones que se van a ejecutar despues de cada test.
@AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }

}

