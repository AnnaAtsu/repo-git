package org.example.forinterface;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginUser implements Login{

    WebDriver driver = new ChromeDriver();
    @Override
    public void submitbutton() {
      driver.findElement(By.name(    }
}
