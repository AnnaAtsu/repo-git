package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;


public class TestBase {


    WebDriver driver;

    public TestBase(WebDriver driver) {
        this.driver = driver;
    }

    @BeforeEach
    public void setup() {
        driver = SetUp.getDriver();
    }

    @AfterAll
    public static void tearDownAll() {
        SetUp.quitDriver();
    }

        void openMainPage() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/takan/IdeaProjects/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
    }


    void clickAddButton() {
        WebDriverManager.chromedriver().setup(); // автоматически скачает и настроит ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.findElement(By.xpath("//button[contains(@class, 'oxd-button') and contains(., 'Add')]")).click();
    }

    public boolean isElementVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

}
