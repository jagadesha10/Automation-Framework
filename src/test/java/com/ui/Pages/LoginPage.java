package com.ui.Pages;

import com.ui.Browser.Utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BrowserUtility {

    private static final By EMAIL_TEXT_BOX_LOCATOR= By.id("email");
    private static final By PASSWORD_TEXT_BOX_LOCATOR= By.id("passwd");
    private static final By SUBMIT_BUTTON_LOCATOR= By.id("SubmitLogin");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public MyAccountPage doLoginWith(String emailAddress,String password){
        enterText(EMAIL_TEXT_BOX_LOCATOR, emailAddress);
        enterText(PASSWORD_TEXT_BOX_LOCATOR,password);
        clickOn(SUBMIT_BUTTON_LOCATOR);
        MyAccountPage myAccountPage= new MyAccountPage(getDriver());
        return myAccountPage;
    }
}
