package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
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


    private static WebDriver driver;


    private SetUp() {}

    // Публичный статический метод доступа к единственному экземпляру драйвера
    public static WebDriver getDriver() {
        if (driver == null) {
           WebDriverManager.chromedriver().setup();
            //  System.setProperty("webdriver.chrome.driver", "C:/Users/takan/IdeaProjects/chromedriver-win64/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    // Метод для закрытия драйвера (например, в @AfterAll)
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
































  //  public static WebDriver driver;

  //  public void init(String browser, Properties properties) {

  //  }
   // @BeforeAll

   // public static void setup() {
    //  System.setProperty("webdriver.chrome.driver", "C:/Users/takan/IdeaProjects/chromedriver-win64/chromedriver.exe");
    //  WebDriver driver = new ChromeDriver();
             //создание экземпляра драйвера
     //   driver.manage().window().maximize();
        //окно разворачивается на полный экран
    //    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
  //  }


 // @AfterEach
  //  void tearDown() {
   //     if (driver != null) {
    //        driver.quit();
    //    }
   // }

}
