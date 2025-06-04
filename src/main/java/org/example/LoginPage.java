package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage {

    @Test
    void LoginUser() {
        //чтобы не указывать путь до драйвера напрямую
        WebDriverManager.chromedriver().setup(); // автоматически скачает и настроит ChromeDriver
        WebDriver driver = new ChromeDriver();
       // System.setProperty("webdriver.chrome.driver", "C:/Users/takan/IdeaProjects/chromedriver-win64/chromedriver.exe");
        //WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        //driver.findElement(By.name("username")).click();
        //driver.findElement(By.name("username")).sendKeys("Admin");
        WebElement username = driver.findElement(By.name("username"));
        Actions actions = new Actions(driver);
        actions.click(username)
                .sendKeys(username, "Admin")
                .perform();
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        Actions actions1 = new Actions(driver);
        actions1.click(password)
                .sendKeys(password, "admin123")
                .perform();
        //driver.findElement(By.xpath("//input[@type='password']")).click();
        //driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).submit();
        String title = driver.getTitle();
        AssertJUnit.assertEquals("OrangeHRM", title);
        String adminName = driver.findElement(By.xpath("//h6[text()='Dashboard']")).getText();
        AssertJUnit.assertEquals(adminName, "Dashboard");

    }


}
