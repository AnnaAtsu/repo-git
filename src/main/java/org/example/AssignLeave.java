package org.example;

import org.example.generator.AssignLeaveGenerator;
import org.example.generator.AssingLeaveVO;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class AssignLeave {
    WebDriver driver;

@Test
    void addAssignLeave() {
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
    //найти раздел assignleave
    driver.findElement(By.xpath("//span[text()=\"Leave\"]")).click();
    driver.findElement(By.xpath("//a[text()=\"Assign Leave\"]")).click();

    AssingLeaveVO newAssignLeave = AssignLeaveGenerator.generateAssign();
   driver.findElement(By.cssSelector("[placeholder='Type for hints...']")).click();
    driver.findElement(By.cssSelector("[placeholder='Type for hints...']")).sendKeys( "j");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
    //выбрать 1 элемент из списка
    driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[@role=\"option\"][1]")).click();
    // выбрать тип лива
    //driver.findElement(By.cssSelector("oxd-icon bi-caret-down-fill oxd-select-text--arrow")).click();
    //driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[@role=\"option\"][4]")).click();
    //указать даты
    List<WebElement> inputs = driver.findElements(By.cssSelector("[placeholder='yyyy-dd-mm']"));
    inputs.get(0).sendKeys(newAssignLeave.getFromDate());
    inputs.get(1).sendKeys(newAssignLeave.getFromDate());
    driver.findElement(By.tagName("textarea")).sendKeys(newAssignLeave.getComments());

   driver.findElement(By.xpath("//*[@type='submit']")).click();


}

}
