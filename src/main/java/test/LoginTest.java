package test;

import org.example.LoginPage;
import org.example.LoginPageObject;
import org.example.SetUp;
import org.example.TestBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

public class LoginTest extends TestBase {


    WebDriver driver;
    LoginPageObject loginPage;

    public LoginTest(WebDriver driver) {
        super(driver);
    }

    @BeforeEach
    public void setUp() {
        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        loginPage = new LoginPageObject(driver);
    }

    @Test
    @Step("Логин с логином Admin и паролем admin123")
    public void testSuccessfulLogin() {
        loginPage.loginAs("Admin", "admin123");
        String title = driver.getTitle();
        AssertJUnit.assertEquals("OrangeHRM", title);
        String adminName = driver.findElement(By.xpath("//h6[text()='Dashboard']")).getText();
        AssertJUnit.assertEquals(adminName, "Dashboard");
    }


    @Test
    @Step("Логаут с логином Admin и паролем admin123")
    public void testSuccessfullLogout() {
        loginPage.loginAs("Admin", "admin123");
        String title = driver.getTitle();
        AssertJUnit.assertEquals("OrangeHRM", title);
        String adminName = driver.findElement(By.xpath("//h6[text()='Dashboard']")).getText();
        AssertJUnit.assertEquals(adminName, "Dashboard");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        loginPage.logOut();
        String loginUrl = driver.getCurrentUrl();
        AssertJUnit.assertEquals(loginUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.quit();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshotOnFailure();
        }
        driver.quit();
    }
    @AfterAll
    public static void tearDown() {
        SetUp.quitDriver();
    }
}
