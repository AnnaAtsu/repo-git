package org.example.forinterface;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public interface Login {

WebDriver driver = new ChromeDriver();

    void submitbutton();

    
   default void enterusername() {
        driver.findElement(By.name("username"));
    }

    default void enterpassword() {
        driver.findElement(By.name("password"));
    }

}
