package com.ui.Test;

import com.constants.Browser;
import com.ui.Pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class LoginTestNew {

    HomePage homePage;

    @BeforeMethod(description = "Load the Homepage of the website")
    public void setUp(){
        homePage=new HomePage(Browser.CHROME, true);
    }

    @Test(description ="Verifies with the valid user is able to login into the application", groups = {"e2e", "sanity"})
    public void LoginTest() {

        assertEquals(homePage.goToLoginPage().doLoginWith("lofegon421@2insp.com","Password1234").getUserName(),"Jagadish R");
    }
}
