package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static SetUp.SetUp.driver;

public class AdminPage {


    void LoginAdmin() {
        driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
        driver.findElement(By.id("login")).sendKeys("admin");
        WebElement dropdownElement = driver.findElement(By.id("user-role"));
        Select select = new Select(dropdownElement);
        select.selectByVisibleText("Admin");
        driver.findElement(By.xpath("//button[contains(text(), 'Search'")).click();
        driver.findElement(By.cssSelector("input.add")).submit();


    }

}
