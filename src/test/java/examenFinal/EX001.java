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
import org.openqa.selenium.support.ui.Select;
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
        String email="orlando.martinez@dominio2.com";
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
        //Búsqueda y selección del item
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
                ultimoItemListado.click();
                System.out.println("El nombre del producto seleccionado es :"+ultimoNombreListado);
                
            } 
            
            WebElement precioItem=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='current-price-value']")));           
            String precioItemSeleccionado=precioItem.getText();
            Assertions.assertTrue((precioItemSeleccionado != null && !precioItemSeleccionado.isBlank()),"No se ha cargado el precio del articulo a agregar correctamente.");
     
            WebElement botonAgregarAlCarrito=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary add-to-cart']")));           
            botonAgregarAlCarrito.click();
            WebElement botonProceedCheckout=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-content-btn']//a[@class='btn btn-primary']")));           
            botonProceedCheckout.click();
            WebElement totalCompra=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='value']")));           
            String montoTotal=totalCompra.getText();
            Assertions.assertTrue((montoTotal != null && !montoTotal.isBlank()),"No hay un monto para cobrar");
            
            //Validar si se ha cargado correctamente un producto al carrito durante el proceso de compra, si no hay falla el test
            boolean sinItemsCarrito = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='no-items']")));
            Assertions.assertTrue(sinItemsCarrito,"No hay items seleccionados para continuar el proceso correctamente");
            
            WebElement botonProceedCheckout2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Proceed to checkout')]")));           
            botonProceedCheckout2.click();

            //LLenado de información del cliente

            WebElement campoaddAddress=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("field-address1")));           
            campoaddAddress.sendKeys("Dirección Test");
            WebElement campoaddCity=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("field-city")));           
            campoaddCity.sendKeys("Ciudad Test");
            WebElement addEstados = driver.findElement(By.id("field-id_state"));
            Select seleccionarEstado = new Select(addEstados);
            seleccionarEstado.selectByVisibleText("California");
            WebElement campoaddZip=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("field-postcode")));           
            campoaddZip.sendKeys("12345");
             WebElement addPais = driver.findElement(By.id("field-id_country"));
            Select seleccionarPais = new Select(addPais);
            seleccionarPais.selectByVisibleText("United States");
            WebElement botonAddAddressContinuar=wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("confirm-addresses")));           
            botonAddAddressContinuar.click();
            WebElement botonconfirmDeliveryOption=wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("confirmDeliveryOption")));           
            botonconfirmDeliveryOption.click();            
            WebElement metodoPagoOpcion = driver.findElement(By.id("payment-option-1"));
            metodoPagoOpcion.click();                  
            WebElement botonConfirmarCondiciones=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='js-terms']")));           
            botonConfirmarCondiciones.click();   
            WebElement botonPlaceOrder=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Place order']")));           
            botonPlaceOrder.click();  

            //Validación de orden completada correctamente
            WebElement mensajeOrdenConfirmada=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='h1 card-title']")));           
            String confirmacionOrden=mensajeOrdenConfirmada.getText();
            String confirmacionOrdenEsperado="Your order is confirmed";
            System.out.println("Obtenida: "+confirmacionOrden+" Esperada: "+confirmacionOrdenEsperado);
            Assertions.assertTrue(confirmacionOrden.contains(confirmacionOrdenEsperado.toUpperCase()),"La orden no pudo ser procesada correctamente.");
                     
           

}
    
// Acciones que se van a ejecutar despues de cada test.
@AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }

}

