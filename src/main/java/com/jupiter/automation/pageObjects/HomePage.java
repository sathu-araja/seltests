package com.jupiter.automation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
    public WebDriver driver;

    public HomePage(){}

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //private By homeLink = By.xpath("//li[@id='nav-home']/a[.='Home']");

    //private By contactLink = By.xpath("//li[@id='nav-contact']/a[.='Contact']");

    @FindBy(how = How.XPATH, using = "//li[@id='nav-home']/a[.='Home']")
    private WebElement homeLink;

    @FindBy(how = How.XPATH, using = "//li[@id='nav-contact']/a[.='Contact']")
    private WebElement contactLink;

    @FindBy(how = How.XPATH, using = "//li[@id='nav-shop']/a[.='Shop']")
    private WebElement shopLink;

    @FindBy(how = How.ID, using = "nav-cart")
    private WebElement cartLink;

    public HomePage navigateToContactPage()
    {
        contactLink.click();
        return this;
    }

    public HomePage navigateToShopPage()
    {
        shopLink.click();
        return this;
    }

    public HomePage navigateToCartPage()
    {
        cartLink.click();
        return this;
    }
}
