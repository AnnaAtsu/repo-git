package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.example.SetUp.driver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage extends TestBase{

  @Test
    void LoginUser() {
   System.setProperty("webdriver.chrome.driver", "C:/Users/takan/IdeaProjects/chromedriver-win64/chromedriver.exe");
   WebDriver driver = new ChromeDriver();
     driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
      driver.findElement(By.name("username")).click();
      driver.findElement(By.name("username")).sendKeys("Admin");
      driver.findElement(By.xpath("//input[@type='password']")).click();
      driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
      driver.findElement(By.tagName("button")).submit();
      String title = driver.getTitle();
     AssertJUnit.assertEquals("OrangeHRM", title);
     String userName = String.valueOf(driver.findElement(By.className("oxd-userdropdown-name")));
     assertTrue(true, userName);

    }


}
