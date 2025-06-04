package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;


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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//button[contains(@class, 'oxd-button') and contains(., 'Add')]")).click();
        //выбрать user role

        driver.findElement(By.xpath("//div[contains(@class, 'oxd-select-text--after')]//i[contains(@class, 'oxd-icon')]")).click();
        List<WebElement> options = driver.findElements(By.xpath("//div[contains(@class, 'oxd-select-text-input') and contains(., 'Admin')]"));
        options.get(0).click();
      // driver.findElement(By.xpath("//div[contains(@class, 'oxd-select-text-input') and (text())='Admin']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //выбрать status
       // driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[2]/i")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //выбрать employee name
        driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("John Smith");
        //указать юзернейм
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input")).sendKeys("Anna12");
        //указать пароль
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//input[@type='password']")).click();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password123456");
        //подтвердить пароль
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")).sendKeys("password123456");

    }
}