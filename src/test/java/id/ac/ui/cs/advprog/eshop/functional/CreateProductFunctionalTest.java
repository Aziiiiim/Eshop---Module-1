package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    private String baseUrl;

    @BeforeEach
    void setUpTest() {
        baseUrl = "http://localhost:" + serverPort + "/product/create";
    }

    @Test
    void testCreateProduct(ChromeDriver driver) throws Exception {
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
