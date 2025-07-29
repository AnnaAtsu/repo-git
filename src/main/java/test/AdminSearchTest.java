package test;

import org.example.AdminPageObject;
import org.example.LoginPageObject;
import org.example.SetUp;
import org.example.UserRowElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.time.Duration;
import java.util.List;


public class AdminSearchTest {
    WebDriver driver;
    AdminPageObject adminPage;
    LoginPageObject loginPage;


    @BeforeEach
    public void setUp() {
        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

    }

    @Test
    public void testAdminGotoSystemUsers() {
        loginPage = new LoginPageObject(driver);
        loginPage.loginAs("Admin", "admin123");

        Assert.assertEquals(driver.getTitle(), "OrangeHRM");

        adminPage = new AdminPageObject(driver);
        adminPage.navigateToUserSearch();
        adminPage.filterByRoleAndUsername("Admin", "Admin");
        adminPage.openUserDropdown();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        WebElement path = driver.findElement(By.cssSelector("div[data-v-6c07a142='']"));
        String adminText = path.getText();
        Assert.assertEquals(adminText, "Admin");
        //  ДЛЯ ШАБЛОНА Page element
        List<UserRowElement> users = adminPage.getUserRows();
               for (UserRowElement user : users) {
            if (user.getUsername().equals("Admin")) {
                Assert.assertEquals(user.getStatus(), "Enabled");
            }
        }
    }


    @AfterAll
    public static void tearDown() {
        SetUp.quitDriver();
    }

}





