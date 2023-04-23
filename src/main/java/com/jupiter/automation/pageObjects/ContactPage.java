package com.jupiter.automation.pageObjects;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class ContactPage
{
    private WebDriver driver;
    public ContactPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "forename")
    private WebElement forenameTxtBox;

    @FindBy(how = How.ID, using = "surname")
    private WebElement surnameTxtBox;

    @FindBy(how = How.ID, using = "email")
    private WebElement emailTxtBox;

    @FindBy(how = How.ID, using = "telephone")
    private WebElement telephoneTxtBox;

    @FindBy(how = How.ID, using = "message")
    private WebElement messageTxtBox;

    @FindBy(how = How.XPATH, using = "//a[.='Submit']")
    private WebElement submitBtn;

    @FindBy(how = How.ID, using = "forename-err")
    private WebElement foreNameMissingErrorMsg;

    @FindBy(how = How.ID, using = "email-err")
    private WebElement emailMissingErrorMsg;

    @FindBy(how = How.ID, using = "message-err")
    private WebElement messageMissingErrorMsg;

    @FindBy(how = How.XPATH, using = "//a[@class='btn' and contains(., 'Back')]")
    private WebElement backBtn;

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-success']")
    private WebElement submitSuccessMsg;

    public void clickSubmit()
    {
        submitBtn.click();
    }

    public String foreNameErrorMessage() {
        if (foreNameMissingErrorMsg != null && foreNameMissingErrorMsg.isDisplayed()) {
            System.out.println("ForeName error text dispalyed is :" +foreNameMissingErrorMsg.getAttribute("innerText"));
            return foreNameMissingErrorMsg.getAttribute("innerText");
        } else {
            System.out.println("Foreman error text was not displayed");
        }
        return null;
    }

    public String emailErrorMessage() {
        if (emailMissingErrorMsg != null && emailMissingErrorMsg.isDisplayed()) {
            return emailMissingErrorMsg.getAttribute("innerText");
        } else {
            System.out.println("Email error text was not displayed");
        }
        return null;
    }

    public String messageBoxErrorMessage() {
        if (messageMissingErrorMsg != null && messageMissingErrorMsg.isDisplayed()) {
            return messageMissingErrorMsg.getAttribute("innerText");
        } else {
            System.out.println("Message box error text was not displayed");
        }
        return null;
    }

    public ContactPage fillInForeName(String surname)
    {
        forenameTxtBox.sendKeys(surname);
        return this;
    }

    public ContactPage fillInEmailAddress(String emailId)
    {
        emailTxtBox.sendKeys(emailId);
        return this;
    }

    public ContactPage fillInMessage(String customerQuery)
    {
        messageTxtBox.sendKeys(customerQuery);
        return this;
    }

    public ContactPage confirmThatValidationMessagesAreNotDisplayed()
            throws InterruptedException
    {
        Thread.sleep(2000L);
        if(forenameTxtBox.getAttribute("class") != "ng-pristine ng-invalid ng-invalid-required"){
            System.out.println("foreName validation message is no longer displayed");
        }
        else{
            Assert.isTrue(false, "foreName validation message is Still displayed");
        }
        if(emailTxtBox.getAttribute("class") != "ng-pristine ng-valid-email ng-invalid ng-invalid-required"){
            System.out.println("Email validation message is no longer displayed");
        }
        else{
            Assert.isTrue(false, "Email validation message is no longer displayed");
        }
        if(messageTxtBox.getAttribute("class") != "ng-pristine ng-invalid ng-invalid-required"){
            System.out.println("Message box validation message is no longer displayed");
        }
        else{
            Assert.isTrue(false, "Message box validation message is no longer displayed");
        }
        return this;
    }

    public String getSubmitSuccessMsg()
    {
        System.out.println("SUCCESS MSG IS : " +submitSuccessMsg.getAttribute("innerText"));
        return submitSuccessMsg.getAttribute("innerText");
    }

    public void clickBack()
    {
       backBtn.click();
    }

    public void waitForSendingFeedbackProgressBar()
            throws InterruptedException {
        WebElement progressBar;
        do{
            Thread.sleep(1000L);
            try {
                progressBar = driver.findElement(By.xpath("//div[@class='progress progress-info wait']"));
                System.out.println("Waiting for progress bar to complete sending feedback");
            }catch(Exception e)
            {
                System.out.println("Progress bar loading complete");
                return;
            }
        }while(progressBar !=null);;
    }
}
