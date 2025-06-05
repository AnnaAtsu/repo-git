package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class AdminPageObject extends TestBase{

    WebDriver driver;

    // Элементы страницы
    @FindBy(name = "username")
    WebElement usernameInput;

    @FindBy(name = "password")
    WebElement passwordInput;

    @FindBy(tagName = "button")
    WebElement loginButton;

    @FindBy(className = "oxd-main-menu-item")
    WebElement adminMenu;

    @FindBy(xpath = "//div[contains(@class, 'oxd-select-text--after')]//i[contains(@class, 'oxd-icon')]")
    WebElement dropDownMenuRole;

    @FindBy(xpath = "//div[@role='option' and contains(@class, 'oxd-select-option')][.//span[text()='Admin']]")
    WebElement adminRole;

    @FindBy(xpath = "//div[2]/input")
    WebElement searchFieldUsername;

    @FindBy(xpath = "//button[contains(.,'Search')]")
    WebElement searchUserButton;

    // Конструктор
    public AdminPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // инициализация элементов
    }

    // Действия
    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.submit();
    }

    public void clickAdminMenu() {
       adminMenu.click();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void chooseUserRole() {
        dropDownMenuRole.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        adminRole.click();

    }
    public void searchUserName() {
        searchFieldUsername.sendKeys("Admin");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void clickSearchButton() {
        searchUserButton.click();
    }

    public void LoginAndSearchForAdmin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        clickAdminMenu();
        chooseUserRole();
        searchUserName();
        clickSearchButton();

    }
}
