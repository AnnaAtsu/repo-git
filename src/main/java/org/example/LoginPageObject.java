package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends TestBase {
    WebDriver driver;


    // Элементы страницы
    @FindBy(name = "username")
    WebElement usernameInput;

    @FindBy(name = "password")
    WebElement passwordInput;

    @FindBy(tagName = "button")
    WebElement loginButton;

    // Конструктор
    public LoginPageObject(WebDriver driver) {
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

    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }


}
