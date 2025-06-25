package test;

import org.example.DashboardPageObject;
import org.example.LoginPageObject;
import org.example.SetUp;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;

import java.time.Duration;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardElementsTest {
    WebDriver driver;
    @Test
    public void checkDashboardElements() {
        WebDriver driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        LoginPageObject loginPage = new LoginPageObject(driver);
        loginPage.loginAs("Admin", "admin123");

        DashboardPageObject dashboard = new DashboardPageObject(driver);
        AssertJUnit.assertTrue(dashboard.isDashboardLoaded());

        // Проверка виджетов
        AssertJUnit.assertTrue(dashboard.isWidgetVisible("Time at Work"));
        AssertJUnit.assertTrue(dashboard.isWidgetVisible("My Actions"));
        AssertJUnit.assertTrue(dashboard.isWidgetVisible("Quick Launch"));
        AssertJUnit.assertTrue(dashboard.isWidgetVisible("Buzz Latest Posts"));
        AssertJUnit.assertTrue(dashboard.isWidgetVisible("Employees on Leave Today"));
        AssertJUnit.assertTrue(dashboard.isWidgetVisible("Employee Distribution by Sub Unit"));
        AssertJUnit.assertTrue(dashboard.isWidgetVisible("Employee Distribution by Location"));

        // Графики
        AssertJUnit.assertTrue(dashboard.isChartVisible("emp-attendance-chart"));
        AssertJUnit.assertTrue(dashboard.isChartVisible("oxd-pie-chart"));

        //ToDo лист
        AssertJUnit.assertTrue(dashboard.isChartVisible("orangehrm-todo-list"));

        // Кнопки
        AssertJUnit.assertTrue(dashboard.isButtonVisible("Assign Leave"));
        AssertJUnit.assertTrue(dashboard.isButtonVisible("Leave List"));
        AssertJUnit.assertTrue(dashboard.isButtonVisible("Timesheets"));
        AssertJUnit.assertTrue(dashboard.isButtonVisible("Apply Leave"));
        AssertJUnit.assertTrue(dashboard.isButtonVisible("My Leave"));
        AssertJUnit.assertTrue(dashboard.isButtonVisible("My Timesheet"));

        driver.quit();
    }
}
