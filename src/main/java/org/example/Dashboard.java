package org.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.time.Duration;
import java.util.NoSuchElementException;

public class Dashboard{

    WebDriver driver;

   @Test
    public void checkElementsDashboard() {
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
       String currentUrl = driver.getCurrentUrl();
       AssertJUnit.assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", currentUrl);
       //один способ
       WebElement timeAtWork = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p' and text()='Time at Work']"));
       Assert.assertTrue(timeAtWork.isDisplayed());
       //второй способ
       Assert.assertTrue(isElementVisible(By.xpath("//p[@class='oxd-text oxd-text--p' and text()='Time at Work']")));
       Assert.assertTrue(isElementVisible(By.xpath("//p[@class='oxd-text oxd-text--p' and text()='My Actions']")));
       //Assert.assertTrue(isElementVisible(By.xpath("//*[contains(text(), 'Time at Work')]")));
       Assert.assertTrue(isElementVisible(By.xpath("//p[@class='oxd-text oxd-text--p' and text()='Quick Launch']")));
       Assert.assertTrue(isElementVisible(By.xpath("//p[@class='oxd-text oxd-text--p' and text()='Buzz Latest Posts']")));
       Assert.assertTrue(isElementVisible(By.xpath("//p[@class='oxd-text oxd-text--p' and text()='Employees on Leave Today']")));
       Assert.assertTrue(isElementVisible(By.xpath("//p[@class='oxd-text oxd-text--p' and text()='Employee Distribution by Sub Unit']")));
       Assert.assertTrue(isElementVisible(By.xpath("//p[@class='oxd-text oxd-text--p' and text()='Employee Distribution by Location']")));
       Assert.assertTrue(isElementVisible(By.className("emp-attendance-chart")));
       Assert.assertTrue(isElementVisible(By.className("oxd-pie-chart")));
       Assert.assertTrue(isElementVisible(By.className("orangehrm-todo-list")));
       Assert.assertTrue(isElementVisible(By.xpath("//h6[text()='Dashboard']")));
       Assert.assertTrue(isElementVisible(By.xpath("//button[@title='Assign Leave']")));
       Assert.assertTrue(isElementVisible(By.xpath("//button[@title='Leave List']")));
       Assert.assertTrue(isElementVisible(By.xpath("//button[@title='Timesheets']")));
       Assert.assertTrue(isElementVisible(By.xpath("//button[@title='Apply Leave']")));
       Assert.assertTrue(isElementVisible(By.xpath("//button[@title='My Leave']")));
       Assert.assertTrue(isElementVisible(By.xpath("//button[@title='My Timesheet']")));
       driver.quit();
    }

    public boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
