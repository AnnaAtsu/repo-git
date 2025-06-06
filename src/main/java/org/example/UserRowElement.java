package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserRowElement {


    private WebElement rootElement;

    public UserRowElement(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    public String getUsername() {
        return rootElement.findElement(By.xpath(".//div[1]")).getText();
    }

    public String getRole() {
        return rootElement.findElement(By.xpath(".//div[2]")).getText();
    }

    public String getStatus() {
        return rootElement.findElement(By.xpath(".//div[3]")).getText();
    }
}
