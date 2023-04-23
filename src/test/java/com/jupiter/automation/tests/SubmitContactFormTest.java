package com.jupiter.automation.tests;

import com.jupiter.automation.Utilities.WaitUtils;
import com.jupiter.automation.pageObjects.ContactPage;
import com.jupiter.automation.pageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SubmitContactFormTest
        extends TestBase
{
    @Test(invocationCount = 5)
    public void submitContactformTest()
            throws IOException, InterruptedException
    {
        new HomePage(driver)
                .navigateToContactPage();

        ContactPage contactPage = new ContactPage(driver);

        WaitUtils.waitFor(2000L);

        contactPage.fillInForeName("test")
                .fillInEmailAddress("t@gm.com")
                .fillInMessage("query")
                .clickSubmit();

        contactPage.waitForSendingFeedbackProgressBar();

        Assert.assertEquals(contactPage.getSubmitSuccessMsg(), "Thanks test, we appreciate your feedback.");

        contactPage.clickBack();

        driver.close();
    }
}
