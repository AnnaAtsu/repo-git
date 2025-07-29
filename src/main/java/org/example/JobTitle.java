package org.example;

import dev.failsafe.internal.util.Assert;
import org.example.generator.JobTitleGenerator;
import org.example.generator.JobVO;
import org.example.generator.UserGenerator;
import org.example.generator.UserVO;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobTitle {

    WebDriver driver;



   @RepeatedTest(3)
   // @Test
    void AddJobTitle() {

        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("Admin");
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        Actions actions1 = new Actions(driver);
        actions1.click(password)
                .sendKeys(password, "admin123")
                .perform();
        driver.findElement(By.tagName("button")).submit();
        driver.findElement(By.className("oxd-main-menu-item")).click();
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
         // найти раздел job
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // найти ссылку jpb title
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[1]/a")).click();
        String expectedUrlJobTitle = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewJobTitleList";
        String actualUrlJobTitle = driver.getCurrentUrl();
        AssertJUnit.assertEquals(expectedUrlJobTitle, actualUrlJobTitle);
        //нажать на кнопку Add
        driver.findElement(By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary")).click();
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Создание VO
        JobVO newJob = JobTitleGenerator.generateTitle();
        driver.findElement(By.xpath("(//input[contains(@class, 'oxd-input') and contains(@class, 'oxd-input--active')])[2]")).sendKeys(newJob.getJobTitle());
        driver.findElement(By.xpath("//textarea[@placeholder='Type description here']")).sendKeys(newJob.getJobDescription());
        driver.findElement(By.xpath("//textarea[@placeholder='Add note']")).sendKeys(newJob.getJobNote());
        driver.findElement(By.cssSelector("button.oxd-button.oxd-button--secondary.orangehrm-left-space")).click();
     //   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        String xpath = "//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell']//div[text()='" + newJob.getJobTitle() + "']";
        WebElement jobElement = driver.findElement(By.xpath(xpath));
        assertEquals(jobElement.getText(), newJob.getJobTitle());
        //driver.quit();
    }


    @Test
    void DeleteJobTitle() {
        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("Admin");
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        Actions actions1 = new Actions(driver);
        actions1.click(password)
                .sendKeys(password, "admin123")
                .perform();
        driver.findElement(By.tagName("button")).submit();
        driver.findElement(By.className("oxd-main-menu-item")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // найти раздел job
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // найти ссылку jpb title
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[1]/a")).click();
        String expectedUrlJobTitle = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewJobTitleList";
        String actualUrlJobTitle = driver.getCurrentUrl();
        AssertJUnit.assertEquals(expectedUrlJobTitle, actualUrlJobTitle);
        // найти иконку корзины и нажать
        driver.findElement(By.cssSelector("button.oxd-icon-button.oxd-table-cell-action-space")).click();

    }

}
