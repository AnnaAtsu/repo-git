package org.example;

import org.example.generator.CandidateGenerator;
import org.example.generator.CandidateVO;
import org.example.generator.JobTitleGenerator;
import org.example.generator.JobVO;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Candidate {
    WebDriver driver;

    @Test
    void addCandidate() {
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
        //найти раздел recrutiment
        driver.findElement(By.xpath("//a[contains(@class,'oxd-main-menu-item')]//span[text()='Recruitment']")).click();
        driver.findElement(By.xpath("//button[contains(@class, 'oxd-button') and contains(., 'Add')]")).click();
        //ввести данные для страницы
        // Создание VO
        CandidateVO newCandidate = CandidateGenerator.generateCandidate();
        WebElement firstName = driver.findElement(By.name("firstName"));
        Actions actions = new Actions(driver);
        actions.click(firstName)
                .sendKeys(firstName, newCandidate.getFirstName())
                .perform();
        WebElement middleName = driver.findElement(By.name("middleName"));
        Actions actions1 = new Actions(driver);
        actions1.click(middleName)
                .sendKeys(middleName, newCandidate.getmiddleName())
                .perform();

    }
}
