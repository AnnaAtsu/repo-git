package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

public class Openbrowser {
    WebDriver driver;

       @Test
    void testOpenGooglePage() {
          //чтобы не указывать путь до драйвера напрямую
         // WebDriverManager.chromedriver().setup(); // автоматически скачает и настроит ChromeDriver
       //   WebDriver driver = new ChromeDriver();
     //System.setProperty("webdriver.chrome.driver", "C:/Users/takan/IdeaProjects/chromedriver-win64/chromedriver.exe");
       //  WebDriver driver = new ChromeDriver();
          driver = SetUp.getDriver();
          driver.get("https://www.google.com/");
        String title = driver.getTitle();

           //как сделать fluent wait
           FluentWait<WebDriver>wait = new FluentWait<>(driver)
                   .withTimeout(Duration.ofSeconds(5))
                   .pollingEvery(Duration.ofSeconds(2))
                   .ignoring(NoSuchElementException.class);
           WebElement button = wait.until(driver -> {
               return driver.findElement(By.xpath("//input[@value='Поиск в Google']"));
           });
       assertEquals("Google", title);
       driver.close();
    }


}
