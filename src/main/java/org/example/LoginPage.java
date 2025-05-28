package org.example;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static SetUp.SetUp.driver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage extends SetUp.SetUp {

    @Test
    void LoginUser() {
      driver.findElement(By.id("login")).click();
      driver.findElement(By.id("login")).sendKeys("Anna");
      driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("password");
      driver.findElement(By.tagName("button")).submit();
      String title = driver.getTitle();
      AssertJUnit.assertEquals("Login", title);

    }


}
