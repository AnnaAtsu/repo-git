package org.example.forinterface;

import org.example.ConfProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public interface Login {



WebDriver driver = new ChromeDriver();



    void submitbutton();

    
   default void enterusername() {

       String username = ConfProperties.getProperty("adminLogin");
       driver.findElement(By.name("username")).sendKeys(username);
    }

    default void enterpassword() {
        String password = ConfProperties.getProperty("AdminPass");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
    }

}
