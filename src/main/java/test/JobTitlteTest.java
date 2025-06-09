package test;

import org.example.JobTitle;
import org.example.LoginPageObject;
import org.example.SetUp;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class JobTitlteTest {
    WebDriver driver;
    LoginPageObject loginPage;
    JobTitle jobTitle;
    @BeforeEach
    public void setUp() {
        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        loginPage = new LoginPageObject(driver);
    }




}
