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
import org.testng.AssertJUnit;

import java.time.Duration;
import java.util.List;

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
        //найти раздел recruitment
        driver.findElement(By.xpath("//a[contains(@class,'oxd-main-menu-item')]//span[text()='Recruitment']")).click();
        driver.findElement(By.xpath("//button[contains(@class, 'oxd-button') and contains(., 'Add')]")).click();
        //ввести данные для страницы
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/addCandidate";
        AssertJUnit.assertEquals(url, "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/addCandidate");
        // Создание VO
        CandidateVO newCandidate = CandidateGenerator.generateCandidate();
        WebElement firstName = driver.findElement(By.name("firstName"));
        Actions actions = new Actions(driver);
        actions.click(firstName)
                .sendKeys(firstName, newCandidate.getFirstName())
                .perform();
        WebElement middleName = driver.findElement(By.name("middleName"));
        actions1 = new Actions(driver);
        actions1.click(middleName)
                .sendKeys(middleName, newCandidate.getmiddleName())
                .perform();

        WebElement lastName = driver.findElement(By.name("lastName"));
        actions1 = new Actions(driver);
        actions1.click(lastName)
                .sendKeys(lastName, newCandidate.getlastName())
                .perform();
        //найти email
       // driver.findElement(By.xpath("//input[@placeholder='Type here'])[1]")).click();
        //driver.findElement(By.xpath("//input[@placeholder='Type here'])[1]")).sendKeys(newCandidate.getemail());
        //найти контракт
        //driver.findElement(By.xpath("//input[@placeholder='Type here'])[2]")).click();
        //driver.findElement(By.xpath("//input[@placeholder='Type here'])[2]")).sendKeys(newCandidate.getcontractNumber());
        List<WebElement> inputs = driver.findElements(By.cssSelector("[placeholder='Type here']"));
        inputs.get(0).sendKeys(newCandidate.getemail());
        inputs.get(1).sendKeys(newCandidate.getcontractNumber());
        inputs.get(2).sendKeys(newCandidate.getcandidateNote());
        //найти vacancy
        driver.findElement(By.xpath("//i[contains(@class, 'bi-caret-down-fill') and contains(@class, 'oxd-select-text--arrow')]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //driver.findElement(By.xpath("//div[@role='option' and contains(., '" + newCandidate.getvacancy() + "')]")).click();
        driver.findElement(By.xpath("//div[@role='option' and .//span[text()='Junior Account Assistant']]"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // найти keywords
        WebElement keywords = driver.findElement(By.cssSelector("[placeholder='Enter comma seperated words...']"));
        actions = new Actions(driver);
        actions.click(keywords)
                .sendKeys(newCandidate.getkeywords())
                .perform();
       driver.findElement(By.xpath("//i[contains(@class, 'oxd-checkbox-input-icon')]")).click();
        // нажать save
        driver.findElement(By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        //проверки
        WebElement addedFirstName = driver.findElement(By.name("firstName"));
        AssertJUnit.assertEquals(firstName, addedFirstName);
        WebElement addedMiddleName = driver.findElement(By.name("middleName"));
        AssertJUnit.assertEquals(middleName, addedMiddleName);
        WebElement addedLastName = driver.findElement(By.name("lastName"));
        AssertJUnit.assertEquals(lastName, addedLastName);
    }
}
