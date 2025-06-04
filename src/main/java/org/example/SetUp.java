package org.example;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Properties;

import static org.testng.AssertJUnit.assertEquals;


public class SetUp {
    public static WebDriver driver;

    public void init(String browser, Properties properties) {

    }
    @BeforeAll

    public static void setup() {
      System.setProperty("webdriver.chrome.driver", "C:/Users/takan/IdeaProjects/chromedriver-win64/chromedriver.exe");
      WebDriver driver = new ChromeDriver();
             //создание экземпляра драйвера
        driver.manage().window().maximize();
        //окно разворачивается на полный экран
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }


  @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
