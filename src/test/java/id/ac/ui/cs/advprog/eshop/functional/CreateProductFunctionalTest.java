package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    private String baseUrl;
    
    private ChromeDriver driver;
    private ChromeOptions options;
    
    


    @BeforeEach
    void setUpTest() {
    	WebDriverManager.chromedriver().setup();
        baseUrl = "http://localhost:" + serverPort + "/product/create";
        
        options = new ChromeOptions();
        options.addArguments("--headless"); 
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        driver = new ChromeDriver(options);

    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    void testCreateProduct() throws Exception {
    	driver.manage().window().maximize();

        driver.get(baseUrl);

        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.id("submitButton"));

        nameInput.sendKeys("Test Product");
        quantityInput.sendKeys("10");
        submitButton.click();

        WebElement productList = driver.findElement(By.id("productList"));
        String productListText = productList.getText();

        assertTrue(productListText.contains("Test Product"));
        assertTrue(productListText.contains("10"));
    }
}
