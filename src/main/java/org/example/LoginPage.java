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

public class LoginPage extends TestBase{
    WebDriver driver;


    @Test
    void LoginUser() {

        //чтобы не указывать путь до драйвера напрямую
       // WebDriverManager.chromedriver().setup(); // автоматически скачает и настроит ChromeDriver
        //WebDriver driver = new ChromeDriver();

        //WebDriver driver = new ChromeDriver();
        driver = SetUp.getDriver();
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

    @Test
    void logout() {
        driver.findElement( By.cssSelector("oxd-icon.bi-caret-down-fill.oxd-userdropdown-icon")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//a[@href=\"/web/index.php/auth/logout\" and text()=\"Logout\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        String loginUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        AssertJUnit.assertEquals(loginUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");



    }


}
