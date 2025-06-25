package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
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
       assertEquals("Google", title);
    }


}
