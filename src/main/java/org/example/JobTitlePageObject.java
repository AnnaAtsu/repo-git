package org.example;

import org.example.generator.JobVO;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class JobTitlePageObject {
    private By addButton = By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary");
    private By jobTitleInput = By.xpath("(//input[contains(@class, 'oxd-input')])[2]");
    private By descriptionInput = By.xpath("//textarea[@placeholder='Type description here']");
    private By noteInput = By.xpath("//textarea[@placeholder='Add note']");
    private By saveButton = By.cssSelector("button.oxd-button.oxd-button--secondary.orangehrm-left-space");

    //Страница администратора
    private By jobMenu = By.xpath("//nav/ul/li[2]");
    private By jobTitlesLink = By.xpath("//nav/ul/li[2]/ul/li[1]/a");


    WebDriver driver;
    public JobTitlePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void deleteJobByTitle(String jobTitle) {
        String rowXpath = "//div[@role='row']//div[text()='" + jobTitle + "']/ancestor::div[@role='row']";
        String checkboxXpath = rowXpath + "//i[contains(@class, 'oxd-icon bi-check')]";
        String deleteButtonXpath = "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']";
        String confirmDeleteXpath = "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']";

        try {
            WebElement checkbox = driver.findElement(By.xpath(checkboxXpath));
            checkbox.click();

            WebElement deleteButton = driver.findElement(By.xpath("//button[@title='Delete Selected']"));
            deleteButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmDeleteXpath)));

            driver.findElement(By.xpath(confirmDeleteXpath)).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(rowXpath)));
        } catch (NoSuchElementException | TimeoutException e) {
            throw new RuntimeException("Не удалось удалить должность: " + jobTitle, e);
        }
    }


    public void openJobTitles() {
        driver.findElement(jobMenu).click();
       // wait.until(ExpectedConditions.visibilityOfElementLocated(jobTitlesLink));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.findElement(jobTitlesLink).click();
    }

    public void clickAddButton() {
        driver.findElement(addButton).click();
    }

    public void fillJobForm(JobVO job) {
        driver.findElement(jobTitleInput).sendKeys(job.getJobTitle());
        driver.findElement(descriptionInput).sendKeys(job.getJobDescription());
        driver.findElement(noteInput).sendKeys(job.getJobNote());
    }

    public void saveJob() {
        driver.findElement(saveButton).click();
    }

    public boolean isJobPresent(String jobTitle) {
        String xpath = "//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell']//div[text()='" + jobTitle + "']";
        try {
            WebElement jobElement = driver.findElement(By.xpath(xpath));
            return jobElement.getText().equals(jobTitle);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
