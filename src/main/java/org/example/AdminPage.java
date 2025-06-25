package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;

import java.time.Duration;

//import static org.example.SetUp.driver;

public class AdminPage {
    WebDriver driver;



   @Test
    void AdminGotoSystemUsers() {


       //WebDriverManager.chromedriver().setup(); // автоматически скачает и настроит ChromeDriver
      // WebDriver driver = new ChromeDriver();
       // System.setProperty("webdriver.chrome.driver", "C:/Users/takan/IdeaProjects/chromedriver-win64/chromedriver.exe");
        //WebDriver driver = new ChromeDriver();
       driver = SetUp.getDriver();
       driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@type='password']")).click();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).submit();
        String title = driver.getTitle();
        AssertJUnit.assertEquals("OrangeHRM", title);
        // новый сценарий
        driver.findElement(By.className("oxd-main-menu-item")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
       //выбрать user role

       driver.findElement(By.xpath("//div[contains(@class, 'oxd-select-text--after')]//i[contains(@class, 'oxd-icon')]")).click();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
       driver.findElement(By.xpath("//div[@role='option' and contains(@class, 'oxd-select-option')][.//span[text()='Admin']]")).click();

       // ввести username
       driver.findElement(By.xpath("//div[2]/input")).click();
       driver.findElement(By.xpath("//div[2]/input")).sendKeys("Admin");
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
       driver.findElement(By.xpath("//button[contains(.,'Search')]")).click();


        driver.findElement(By.xpath("//div[2]/i")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        String userDropDown = driver.getCurrentUrl();
        AssertJUnit.assertEquals(userDropDown, "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");

    }

}
