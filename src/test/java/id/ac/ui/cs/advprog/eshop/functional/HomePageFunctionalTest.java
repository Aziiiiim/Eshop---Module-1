package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class HomePageFunctionalTest {
	
	/**
	 * The port number assigned to the running application during test execution.
	 * Set automatically during each test run by Spring Framework's test context.
	 */
	@LocalServerPort
    private int serverPort;
	
	/**
	 * The base URL for testing. Default to {@code http://localhost}.
	 */
	@Value("${app.baseUrl:http://localhost}")
	private String testBaseUrl;
	
	private String baseUrl;

    private ChromeDriver driver;
    private ChromeOptions options;


    @BeforeEach
    void setUpTest() {
    	WebDriverManager.chromedriver().setup();
        baseUrl = String.format("%s:%d",testBaseUrl, serverPort);
        
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
    void pageTitle_isCorrect() throws Exception{
    	// Exercice
    	driver.manage().window().maximize();

    	driver.get(baseUrl);
    	String pageTitle = driver.getTitle();
    	
    	//Verify
    	assertEquals("ADV Shop", pageTitle);
    }

    @Test
    void welcomeMessage_homePage_isCorrect() throws Exception{
    	// Exercice
    	driver.manage().window().maximize();

    	System.out.println(baseUrl);
    	driver.get(baseUrl);
    	String welcomeMessage = driver.findElement(By.tagName("h3")).getText();
    	
    	// Verify
    	assertEquals("Welcome", welcomeMessage);
    }
}
