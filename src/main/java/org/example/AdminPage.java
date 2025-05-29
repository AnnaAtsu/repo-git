package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;

import java.time.Duration;

import static org.example.SetUp.driver;

public class AdminPage {


    void AdminGotoSystemUsers() {

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
        // новый сценарий
        driver.findElement(By.className("oxd-main-menu-item active")).click();
        driver.findElement(By.className("oxd-main-menu-item active")).sendKeys("Admin");
        driver.findElement(By.className("oxd-select-text-input")).click();
        //написать селектор

        driver.findElement(By.className("orangehrm-left-space")).submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//i[contains(text(), ' Add '")).click();

    }

}
