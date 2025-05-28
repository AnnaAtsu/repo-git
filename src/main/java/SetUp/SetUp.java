package SetUp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;


public class SetUp {
    public static WebDriver driver;
    @BeforeEach
    void setup() {
        System.setProperty("webdriver.gecko.driver", "/home/user/idee/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);
        //создание экземпляра драйвера
        driver.manage().window().maximize();
        //окно разворачивается на полный экран
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    void testOpenGooglePage() {
        driver.get("https://www.google.com/");
        String title = driver.getTitle();
        assertEquals("Google", title);
    }
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
