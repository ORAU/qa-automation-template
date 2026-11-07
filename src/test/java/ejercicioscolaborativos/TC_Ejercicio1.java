package ejercicioscolaborativos;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TC_Ejercicio1 {
    // Script para validar el Login Page
    WebDriver driver;
    private WebDriverWait wait;
    private String urlProducto = "https://teststore.automationtesting.co.uk/index.php";

    // Acciones que se van a ejecutar antes de cada test.
    @BeforeEach
    public void inicializarNavegador() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void pausa() throws InterruptedException {
        Thread.sleep(3000);
    }

    // Escenario 1: Visualización del detalle de un producto
    @Test 
    @Order(1)
    public void productDetails() throws InterruptedException {

        // Abrir nuestro navegador
        driver.get(urlProducto);

        // Otener el titulo del sitio web
        String tituloActual = driver.getTitle();
        String tituloEsperado = "Test Store";
        // Validar el titulo del sitio web
        Assertions.assertEquals(tituloEsperado, tituloActual, "El título de la pagina no coincide");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Zoom a 80%
        js.executeScript("document.body.style.zoom='80%'");

        // Scroll
        js.executeScript("window.scrollBy(0,250)");

        // Obtener Nombre de Producto
        WebElement producto = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(
                        ".products.row > div:nth-of-type(2) > .js-product-miniature.product-miniature.reviews-loaded h3 > a")));
        String nombProd = producto.getText();
        System.out.println("Nombre de producto Esperado: " + nombProd);

        // Ir a detalle del Producto
        producto.click();

        pausa();
        // Zoom a 80%
        js.executeScript("document.body.style.zoom='80%'");

        // Validar Url de Pagina detalle de Producto
        String urlEsperada = "https://teststore.automationtesting.co.uk/index.php?id_product=";
     
        Assertions.assertTrue(driver.getCurrentUrl().contains(urlEsperada),
            "Se esperaba la URL: " + urlEsperada + " pero se encontró: " + driver.getCurrentUrl());

        // Validar Nombre del Producto en pantalla detalle del producto
        WebElement prodDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".h1")));//"ol li:nth-of-type(4) span"
        String nombProdDetail = prodDetails.getText();
        System.out.println("Nombre de producto en Detalles: " + nombProdDetail);

        // Validar que nombre de producto sea el mismo 
        Assertions.assertTrue(nombProdDetail.contains(nombProd.toUpperCase()),
            "Se esperaba el nombre de producto: " + nombProd + " pero se encontró: " + nombProdDetail);   
        
        // Imagen de Producto
        WebElement imagenProducto = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".js-images-container div:nth-of-type(1) [alt]")));

        // Validar que la imagen se esté mostrando
        boolean estaDesplegada = imagenProducto.isDisplayed();

        // Validación adicional: Verificar que tenga un enlace de origen de imagen
        // (src) válido
        String srcAtributo = imagenProducto.getAttribute("src");
        boolean tieneSrcValido = srcAtributo != null && srcAtributo.startsWith("http");

        System.out.println("srcAtributo: " + srcAtributo);
        System.out.println("tieneSrcValido: " + tieneSrcValido);

        if (estaDesplegada && tieneSrcValido) {
            System.out.println("¡Validación exitosa! La imagen de la camisa se muestra correctamente.");
            Assertions.assertTrue(estaDesplegada && tieneSrcValido);
        } else {
            System.out.println(
                    "Fallo en la validación: El elemento existe pero no cumple con los criterios de visibilidad.");
        }

        if (imagenProducto.isDisplayed()) {
            System.out.println("Imagen de producto mostrada");
        } else {
            System.out.println("Imagen de producto NO mostrada");
        }

        // Precio de Producto
        WebElement prodDetailsPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".current-price-value")));

        Assertions.assertTrue(prodDetailsPrice.isDisplayed(),  "Precio de producto NO mostrado");

        pausa();
        // Scroll
        js.executeScript("window.scrollBy(0,500)");

        // Descripcion de Producto
        WebElement tabDescription = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("li:nth-of-type(1) > a[role='tab']")));
        tabDescription.click();
        WebElement prodDetailsDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#tab-content [role='tabpanel']:nth-of-type(1) p")));

        Assertions.assertTrue(prodDetailsDescription.isDisplayed(),  "Descripcion de producto NO mostrada");

        // Agregar Producto al Carrito
        WebElement prodDetailsAddToCar = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".btn.btn-primary.add-to-cart")));
        
        Assertions.assertTrue(prodDetailsAddToCar.isDisplayed(),  "Boton Agregar de producto a carrito NO mostrado");
    }

    // Escenario: Agregar producto al carrito
    @Test
    @Order(2)
    public void agregarProductoAlCarrito() throws InterruptedException {
        driver.get(urlProducto);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Zoom a 80%
        js.executeScript("document.body.style.zoom='80%'");
        // Scroll
        js.executeScript("window.scrollBy(0,250)");

        // Obtener Nombre de Producto
        WebElement producto = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(
                        ".products.row > div:nth-of-type(2) > .js-product-miniature.product-miniature.reviews-loaded h3 > a")));

        String nombProd = producto.getText().trim();

        // Ir a detalle del Producto
        producto.click();

        pausa();
        // Zoom a 80%
        js.executeScript("document.body.style.zoom='80%'");

        // Validar Nombre del Producto en pantalla detalle del producto
        WebElement prodDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("ol li:nth-of-type(4) span")));
        String nombProdDetail = prodDetails.getText().trim().toUpperCase();

        Assertions.assertTrue(nombProdDetail.contains(nombProd.toUpperCase()),
            "Se esperaba el nombre de producto: " + nombProd + " pero se encontró: " + nombProdDetail);

        pausa();

        // Agregar Producto al Carrito
        WebElement btnCar = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-to-cart.btn.btn-primary")));
        btnCar.click();

        WebElement msjCar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#myModalLabel")));

        // Valida mensaje de producto agregado exitosamente
           Assertions.assertTrue(msjCar.getText().contains("Product successfully added"), "Fallo al agregar al carrit");

        WebElement prodAgregado = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-name")));
        WebElement cerrarModal = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cart-content-btn .btn-secondary")));
        String prodEnCarrito = prodAgregado.getText().trim();

        //Validar Nombre de Producto Agregado al Carrito
        Assertions.assertEquals(nombProd.toUpperCase(), prodEnCarrito.toUpperCase(),
                "Nombre de Producto agregado al carrito no coincide con el seleccionado");

        pausa();
        cerrarModal.click();

        System.out.println("Nombre Producto Agregado: " + prodEnCarrito);

        // Extraer el valor numérico usando expresiones regulares
        WebElement carIni = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".blockcart .cart-products-count")));
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(carIni.getText());

        int carritoIni = 0;
        if (matcher.find()) {
            carritoIni = Integer.parseInt(matcher.group());
            System.out.println("Cantidad en el Carrito: " + carritoIni);
        }

        // Validar que el valor numerico del carrito sea estrictamente mayor a 0
        assertTrue(carritoIni > 0,
                "El carrito tiene " + carritoIni + " elementos. Se esperaba un valor mayor a cero.");

    }    

    // Acciones que se van a ejecutar despues de cada test.
    @AfterEach
    public void cerrarSesion() {
        if (driver != null) {
            driver.quit();
        }
    }

}
