package com.ui.Pages;

import com.constants.Browser;
import static com.constants.Env.*;
import com.ui.Browser.Utility.BrowserUtility;
import static com.ui.Browser.Utility.PropertiesUtil.*;

import com.ui.Browser.Utility.JSONUtility;
import com.ui.Browser.Utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class HomePage extends BrowserUtility {
    Logger logger= LoggerUtility.getLogger(this.getClass());

    private static final By SIGN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName, isHeadless);
//        goToWebsite(readProperty(QA, "URL"));
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
    }

    public HomePage(WebDriver driver) {
        super(driver);
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
    }


    public LoginPage goToLoginPage(){
        logger.info("Trying to perform click to go to sign in page");
        clickOn(SIGN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage;

    }

    public void quit() {
    }
}
