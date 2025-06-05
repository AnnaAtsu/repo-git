package test;

import org.example.LoginPage;
import org.example.LoginPageObject;
import org.example.SetUp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;

import java.time.Duration;

public class LoginTest {


    WebDriver driver;
    LoginPageObject loginPage;

    @BeforeEach
    public void setUp() {
        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        loginPage = new LoginPageObject(driver);
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.loginAs("Admin", "admin123");
        String title = driver.getTitle();
        AssertJUnit.assertEquals("OrangeHRM", title);
        String adminName = driver.findElement(By.xpath("//h6[text()='Dashboard']")).getText();
        AssertJUnit.assertEquals(adminName, "Dashboard");
    }

    @AfterAll
    public static void tearDown() {
        SetUp.quitDriver();
    }
}
