package com.ui.Test;

import com.constants.Browser;
import com.ui.Browser.Utility.BrowserUtility;
import com.ui.Browser.Utility.LamdaTestUtility;
import com.ui.Browser.Utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import com.ui.Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class TestBase {
    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private boolean isLamdaTest;


    @Parameters({"browser", "isLambdaTest", "isHeadless"})
    @BeforeMethod(description = "Load the Homepage of the website")
    public void setUp(
            @Optional("chrome") String browser,
            @Optional("false") boolean isLamdaTest,
            @Optional("true") boolean isHeadless, ITestResult result) {
        this.isLamdaTest = isLamdaTest;
        WebDriver lamdaDriver;
        if (isLamdaTest) {
            lamdaDriver = LamdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
            homePage = new HomePage(lamdaDriver);

        } else {
            logger.info("Load HomePage of website");
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), true);
        }
    }

    public BrowserUtility getInstance() {
        return homePage;
    }

    @AfterMethod(description = "Tear Down the browser")
    public void tearDown() {
        if (isLamdaTest) {
            LamdaTestUtility.quitSession();
        } else {
            homePage.quit();
        }

    }

}
