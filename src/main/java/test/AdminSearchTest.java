package test;

import org.example.AdminPageObject;
import org.example.LoginPageObject;
import org.example.SetUp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;

import java.time.Duration;

public class AdminSearchTest {
    WebDriver driver;
    AdminPageObject adminpage;


    @BeforeEach
    public void setUp() {
        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        adminpage = new AdminPageObject(driver);
    }

        @Test
        public void testSuccessfullSearch() {
            adminpage.LoginAndSearchForAdmin("Admin", "admin123");
            String title = driver.getTitle();
            AssertJUnit.assertEquals("OrangeHRM", title);

            String userDropDown = driver.getCurrentUrl();
            AssertJUnit.assertEquals(userDropDown, "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");

        }
    @AfterAll
    public static void tearDown() {
        SetUp.quitDriver();
    }

}
