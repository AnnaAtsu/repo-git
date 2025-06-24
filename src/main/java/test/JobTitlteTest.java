package test;

import org.example.*;
import org.example.generator.JobTitleGenerator;
import org.example.generator.JobVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;

import java.time.Duration;

public class JobTitlteTest {
    WebDriver driver;
    LoginPageObject loginPage;
    JobTitle jobTitle;
    @Test
    public void addjobtitle() {
        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        loginPage = new LoginPageObject(driver);
        loginPage.loginAs("Admin", "admin123");
        JobTitlePageObject jobTitlePageObject = new JobTitlePageObject(driver);
        //DashboardPage dashboardPage = new DashboardPage(driver);
        //        dashboardPage.goToAdmin();
        AdminPageObject adminPageObject = new AdminPageObject(driver);
        adminPageObject.navigateToUserSearch();
        jobTitlePageObject.openJobTitles();
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewJobTitleList";
        AssertJUnit.assertEquals(expectedUrl, driver.getCurrentUrl());

        jobTitlePageObject.clickAddButton();
        JobVO newJob = JobTitleGenerator.generateTitle();
        jobTitlePageObject.fillJobForm(newJob);
        jobTitlePageObject.saveJob();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        AssertJUnit.assertTrue(jobTitlePageObject.isJobPresent(newJob.getJobTitle()));

    }

    @RepeatedTest(3)
    public void AddJobTitleRepeatedly(RepetitionInfo repetitionInfo) {
        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        loginPage = new LoginPageObject(driver);
        loginPage.loginAs("Admin", "admin123");
        JobTitlePageObject jobTitlePageObject = new JobTitlePageObject(driver);
        //DashboardPage dashboardPage = new DashboardPage(driver);
        //        dashboardPage.goToAdmin();
        AdminPageObject adminPageObject = new AdminPageObject(driver);
        adminPageObject.navigateToUserSearch();
        jobTitlePageObject.openJobTitles();
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewJobTitleList";
        AssertJUnit.assertEquals(expectedUrl, driver.getCurrentUrl());

        jobTitlePageObject.clickAddButton();
        JobVO newJob = JobTitleGenerator.generateTitle();
        jobTitlePageObject.fillJobForm(newJob);
        jobTitlePageObject.saveJob();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        AssertJUnit.assertTrue(jobTitlePageObject.isJobPresent(newJob.getJobTitle()));
    }


    //с циклом for
    @Test
    public void AddJobTitleMultipleTimes() {
        driver = SetUp.getDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        loginPage = new LoginPageObject(driver);
        loginPage.loginAs("Admin", "admin123");
        JobTitlePageObject jobTitlePageObject = new JobTitlePageObject(driver);
        //DashboardPage dashboardPage = new DashboardPage(driver);
        //        dashboardPage.goToAdmin();
        AdminPageObject adminPageObject = new AdminPageObject(driver);
        adminPageObject.navigateToUserSearch();
        jobTitlePageObject.openJobTitles();
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewJobTitleList";
        AssertJUnit.assertEquals(expectedUrl, driver.getCurrentUrl());


        for (int i = 0; i < 3; i++) {
            jobTitlePageObject.clickAddButton();
            JobVO newJob = JobTitleGenerator.generateTitle(); // каждый раз новая должность
            jobTitlePageObject.fillJobForm(newJob);
            jobTitlePageObject.saveJob();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
            AssertJUnit.assertTrue( jobTitlePageObject.isJobPresent(newJob.getJobTitle()));
        }

        driver.quit();
    }

}
