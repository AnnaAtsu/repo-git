package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddUser {

    @Test
    void addNewUser() {
        WebDriverManager.chromedriver().setup(); // автоматически скачает и настроит ChromeDriver
        WebDriver driver = new ChromeDriver();
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
        driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("John ");
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
        // Получить текст
        String LastAddedUser = userElement.getText();
        assertEquals(LastAddedUser, newUser);

    }
}