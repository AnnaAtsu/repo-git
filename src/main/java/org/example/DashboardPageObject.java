package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPageObject extends TestBase{

    public DashboardPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardLoaded() {
        return isElementVisible(By.xpath("//h6[text()='Dashboard']"));
    }

    public boolean isWidgetVisible(String widgetText) {
        return isElementVisible(By.xpath("//p[@class='oxd-text oxd-text--p' and text()='" + widgetText + "']"));
    }

    public boolean isButtonVisible(String title) {
        return isElementVisible(By.xpath("//button[@title='" + title + "']"));
    }

    public boolean isChartVisible(String className) {
        return isElementVisible(By.className(className));
    }
}
