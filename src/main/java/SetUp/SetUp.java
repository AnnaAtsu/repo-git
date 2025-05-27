package SetUp;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SetUp {

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/user/idee/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com/");
    }

}
