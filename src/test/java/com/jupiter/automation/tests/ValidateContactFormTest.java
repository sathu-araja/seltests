package com.jupiter.automation.tests;

import com.jupiter.automation.Utilities.WaitUtils;
import com.jupiter.automation.pageObjects.ContactPage;
import com.jupiter.automation.pageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ValidateContactFormTest
        extends TestBase
{

   @Test
   public void validateContactformTest()
           throws IOException, InterruptedException
   {

      new HomePage(driver)
              .navigateToContactPage();

      ContactPage contactPage = new ContactPage(driver);

      contactPage.clickSubmit();

      WaitUtils.waitFor(2000L);

      Assert.assertEquals(contactPage.foreNameErrorMessage() , "Forename is required");
      Assert.assertEquals(contactPage.emailErrorMessage() , "Email is required");
      Assert.assertEquals(contactPage.messageBoxErrorMessage() , "Message is required");

      contactPage.fillInForeName("test")
                 .fillInEmailAddress("t@gm.com")
                 .fillInMessage("query")
                 .confirmThatValidationMessagesAreNotDisplayed();
   }
}
