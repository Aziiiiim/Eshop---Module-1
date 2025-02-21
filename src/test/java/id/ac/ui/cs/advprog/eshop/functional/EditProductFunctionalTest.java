package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import id.ac.ui.cs.advprog.eshop.EshopApplication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EshopApplication.class)
@ExtendWith(SeleniumJupiter.class)
public class EditProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    private String baseUrl;
    private ChromeDriver driver;
    private ChromeOptions options;

    @BeforeEach
    void setUpTest() {
        WebDriverManager.chromedriver().setup();
        baseUrl = "http://localhost:" + serverPort + "/product";

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
    void testEditProduct() throws Exception {
        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to the product creation page
        driver.get(baseUrl + "/create");

        // Find the input fields and submit button
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.id("submitButton"));

        // Fill in the product details and submit the form
        nameInput.sendKeys("Old Product Name");
        quantityInput.sendKeys("5");
        submitButton.click();

        // Verify that the product was added successfully
        WebElement productList = driver.findElement(By.id("productList"));
        String productListText = productList.getText();
        assertTrue(productListText.contains("Old Product Name"));
        assertTrue(productListText.contains("5"));

        // Locate and click the edit button for the product
        WebElement editButton = driver.findElement(By.xpath("//*[contains(@id, 'editButton-')]"));
        editButton.click();

        // Find the edit fields and save button
        WebElement editNameInput = driver.findElement(By.id("nameInput"));
        WebElement editQuantityInput = driver.findElement(By.id("quantityInput"));
        WebElement saveButton = driver.findElement(By.id("submitButton"));

        // Modify the product details
        editNameInput.clear();
        editNameInput.sendKeys("New Product Name");
        editQuantityInput.clear();
        editQuantityInput.sendKeys("20");
        saveButton.click();

        // Ensure that the user is redirected to the product list page
        assertEquals(baseUrl + "/list", driver.getCurrentUrl());

        // Verify that the product details were updated correctly
        WebElement updatedProductList = driver.findElement(By.id("productList"));
        String updatedProductListText = updatedProductList.getText();
        assertTrue(updatedProductListText.contains("New Product Name"));
        assertTrue(updatedProductListText.contains("20"));
        assertFalse(updatedProductListText.contains("Old Product Name"));

        // Locate and click the delete button for the product
        WebElement deleteButton = driver.findElement(By.xpath("//*[contains(@id, 'deleteButton-')]"));
        deleteButton.click();

        // Handle the confirmation alert
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // Verify that the product has been deleted
        WebElement finalProductList = driver.findElement(By.id("productList"));
        String finalProductListText = finalProductList.getText();
        assertFalse(finalProductListText.contains("New Product Name"));
    }

}
