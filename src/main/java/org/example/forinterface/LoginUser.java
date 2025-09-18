package org.example.forinterface;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.SetUp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginUser implements Login{

    WebDriver driver = new ChromeDriver();

    @Override
    public void submitbutton() {
        driver.findElement(By.name("submit")).click();

    }


    @Test
    void LoginUserfromInterface() {
    //   setup();
       enterusername();
        enterpassword();
        submitbutton();
        String title = driver.getTitle();
        AssertJUnit.assertEquals("OrangeHRM", title);
        String currentUrl = driver.getCurrentUrl();
        AssertJUnit.assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", currentUrl);
        String adminName = driver.findElement(By.xpath("//h6[text()='Dashboard']")).getText();
        AssertJUnit.assertEquals(adminName, "Dashboard");

    }


}
