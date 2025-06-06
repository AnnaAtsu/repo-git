package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.generator.UserGenerator;
import org.example.generator.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddUser extends TestBase{
    WebDriver driver;


    @Test
    void addNewUser() {
       // WebDriverManager.chromedriver().setup(); // автоматически скачает и настроит ChromeDriver
       // WebDriver driver = new ChromeDriver();
        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@type='password']")).click();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).submit();
        driver.findElement(By.className("oxd-main-menu-item")).click();
        driver.findElement(By.xpath("//button[contains(@class, 'oxd-button') and contains(., 'Add')]")).click();
        //выбрать user role

      driver.findElement(By.xpath("//div[contains(@class, 'oxd-select-text--after')]//i[contains(@class, 'oxd-icon')]")).click();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
      driver.findElement(By.xpath("//div[@role='option' and contains(@class, 'oxd-select-option')][.//span[text()='Admin']]")).click();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //выбрать status
       driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[2]/i")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
       driver.findElement(By.xpath("//div[@role='option' and contains(@class, 'oxd-select-option') and .//span[text()='Disabled']]")).click();

        //выбрать employee name
        driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("Taqi ");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div[2]/div[1]/span")).click();
        //указать юзернейм
        Random rnd = new Random();
        String newUser = "user" +  rnd.nextInt(500);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input")).sendKeys(newUser);
        //указать пароль
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//input[@type='password']")).click();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password123456");
        //подтвердить пароль
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")).sendKeys("password123456");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //нажать на кнопку save
        driver.findElement(By.cssSelector("button.oxd-button.oxd-button--secondary.orangehrm-left-space[type='submit']")).click();
        String MainPageUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";
        assertEquals(MainPageUrl,"https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        String xpath = "//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell']//div[text()='" + newUser + "']";
        WebElement userElement = driver.findElement(By.xpath(xpath));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // Получить текст
        String LastAddedUser = userElement.getText();
        assertEquals(LastAddedUser, newUser);

    }
    // Для шаблона Value Object
    @Test
    public void addNewUserWithRandomData() {
        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        // Login
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).submit();

        // Навигация
        driver.findElement(By.className("oxd-main-menu-item")).click();
        driver.findElement(By.xpath("//button[contains(@class, 'oxd-button') and contains(., 'Add')]")).click();

        // Создание VO
        UserVO newUser = UserGenerator.generate();

        // Выбор роли
        driver.findElement(By.xpath("//div[contains(@class, 'oxd-select-text--after')]//i")).click();
        driver.findElement(By.xpath("//div[@role='option']//span[text()='" + newUser.getRole() + "']")).click();

        // Выбор статуса
        driver.findElement(By.xpath("(//div[contains(@class,'oxd-select-text--after')]//i)[2]")).click();
        driver.findElement(By.xpath("//div[@role='option']//span[text()='" + newUser.getStatus() + "']")).click();

        // Employee name
        driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys(newUser.getEmployeeName());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//span[contains(text(),'" + newUser.getEmployeeName() + "')]")).click();

        // Username
        driver.findElement(By.xpath("//label[text()='Username']/../following-sibling::div/input")).sendKeys(newUser.getUsername());

        // Пароль и подтверждение
        driver.findElement(By.xpath("//label[text()='Password']/../following-sibling::div/input")).sendKeys(newUser.getPassword());
        driver.findElement(By.xpath("//label[text()='Confirm Password']/../following-sibling::div/input")).sendKeys(newUser.getPassword());

        // Сохранить
        driver.findElement(By.cssSelector("button.oxd-button.oxd-button--secondary.orangehrm-left-space")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));


        String xpath = "//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell']//div[text()='" + newUser.getUsername() + "']";
        WebElement userElement = driver.findElement(By.xpath(xpath));
        assertEquals(userElement.getText(), newUser.getUsername());

    }

}