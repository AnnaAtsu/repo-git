package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginFromFile {

    WebDriver driver;


    @Test
    void LoginUserFromFile() {

        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        String baseUrl = ConfProperties.getProperty("baseUrl");
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        String username = ConfProperties.getProperty("adminLogin");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys(username);
        String password = ConfProperties.getProperty("AdminPass");
        driver.findElement(By.xpath("//input[@type='password']")).click();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.tagName("button")).submit();
        String title = driver.getTitle();
        AssertJUnit.assertEquals("OrangeHRM", title);
        String adminName = driver.findElement(By.xpath("//h6[text()='Dashboard']")).getText();
        AssertJUnit.assertEquals(adminName, "Dashboard");
        driver.quit();

    }

}
