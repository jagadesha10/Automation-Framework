package com.ui.Browser.Utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.constants.Browser.CHROME;

public abstract class BrowserUtility {

    //    private WebDriver driver;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public BrowserUtility(Browser browserName, boolean isHeadless, boolean isLambdaTest) {

    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        super();
        this.driver.set(driver);
    }

//    public BrowserUtility(String browserName) {
//        if (browserName.equalsIgnoreCase("chrome")) {
//            driver.set(new ChromeDriver());
//        } else if (browserName.equalsIgnoreCase("Edge")) {
//            driver.set(new EdgeDriver());
//        } else {
//            System.err.print("Invalid Browser Name......Please select Chrome or Edge");
//        }
//    }
//
//    public BrowserUtility(Browser browserName) {
//        if (browserName == Browser.CHROME) {
//            driver.set(new ChromeDriver());
//        } else if (browserName == Browser.EDGE) {
//            driver.set(new EdgeDriver());
//        }
//    }
    public BrowserUtility(Browser browserName, boolean isHeadless) {
        if (browserName == Browser.CHROME ) {
            if (isHeadless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=old");
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
            }
            else {
                driver.set(new ChromeDriver());
            }
        } else if (browserName == Browser.EDGE) {
            driver.set(new EdgeDriver());
        }
    }

    public void goToWebsite(String url) {
        driver.get().get(url);
    }

    public void maximizeWindow() {
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator) {
        WebElement element = driver.get().findElement(locator);
        element.click();
    }

    public void enterText(By locator, String textToEnter) {
        WebElement element = driver.get().findElement(locator);
        element.sendKeys(textToEnter);
    }

    public String getVisibleText(By locator) {
        WebElement element = driver.get().findElement(locator);
        return element.getText();
    }

    public String takeScreenshot(String name) {
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        String timestamp = format.format(date);
        String path = System.getProperty("user.dir") + "//screenshot//" + name + "-" + timestamp + ".png";
        File screenshotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData, screenshotFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
