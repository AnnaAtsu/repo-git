package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class AdminPageObject extends TestBase{

    WebDriver driver;
    public AdminPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "oxd-main-menu-item")
    WebElement adminMenu;

    @FindBy(xpath = "//div[contains(@class, 'oxd-select-text--after')]//i")
    WebElement roleDropdownArrow;

    @FindBy(xpath = "//div[@role='option']//span[text()='Admin']")
    WebElement adminOption;

    @FindBy(xpath = "//div[2]/input")
    WebElement usernameInput;

    @FindBy(xpath = "//button[contains(.,'Search')]")
    WebElement searchButton;

    @FindBy(xpath = "//div[2]/i")
    WebElement userIcon;

    public void navigateToUserSearch() {
        adminMenu.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void filterByRoleAndUsername(String role, String username) {
        roleDropdownArrow.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        adminOption.click();
        usernameInput.sendKeys(username);
        searchButton.click();
    }

    public void openUserDropdown() {
        userIcon.click();
    }







}
