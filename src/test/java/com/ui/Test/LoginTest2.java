package com.ui.Test;

import com.constants.Browser;
import com.ui.Listeners.MyRetryAnalyzer;
import com.ui.Pages.HomePage;
import com.ui.dataproviders.LoginDataProvider;
import com.ui.pojo.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

@Listeners({com.ui.Listeners.TestListener.class})
public class LoginTest2 extends TestBase {





//    @Test(description ="Verifies with the valid user is able to login into the application", groups = {"e2e", "sanity"}, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider ="LoginTestExcelDataProvider")
//    public void LoginTest(User user) {
//        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(),"Jagadish R");
//
//         assertEquals(homePage.goToLoginPage().doLoginWith("lofegon421@2insp.com","Password1234").getUserName(),"Jagadish R");
//    }
    @Test(description = "Verifies with the valid user is able to login into the application", groups = {"e2e", "sanity"}, dataProviderClass = LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider",
    retryAnalyzer = MyRetryAnalyzer.class)
    public void LoginExcelTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Jagadish R");
    }
}
