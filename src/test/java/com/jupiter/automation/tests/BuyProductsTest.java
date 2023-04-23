package com.jupiter.automation.tests;

import com.jupiter.automation.Utilities.WaitUtils;
import com.jupiter.automation.pageObjects.Cartpage;
import com.jupiter.automation.pageObjects.HomePage;
import com.jupiter.automation.pageObjects.ShopPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class BuyProductsTest
            extends TestBase
{

    @Test
    public void buyProductsByNameTest()
            throws InterruptedException
    {

        HomePage homePage = new HomePage(driver);

        homePage.navigateToShopPage();

        ShopPage shopPage = new ShopPage(driver);

        shopPage.buyItemByNameAndQuantityNeeded("Stuffed Frog", 2);
        shopPage.buyItemByNameAndQuantityNeeded("Fluffy Bunny", 5);
        shopPage.buyItemByNameAndQuantityNeeded("Valentine Bear", 3);

        WaitUtils.waitFor(2000L);

        homePage.navigateToCartPage();

        WaitUtils.waitFor(2000L);

        Cartpage cartpage = new Cartpage(driver);

        Assert.assertEquals(cartpage.getItemPriceInTheCartByName(" Stuffed Frog"), "$10.99");
        Assert.assertEquals(cartpage.getItemPriceInTheCartByName(" Fluffy Bunny"), "$9.99");
        Assert.assertEquals(cartpage.getItemPriceInTheCartByName(" Valentine Bear"), "$14.99");

        WaitUtils.waitFor(1000L);

        System.out.println("TOTAL PRICE OF ALL ITEMS IS "+ cartpage.getTotalPriceInTheCart());

        Assert.assertEquals(cartpage.getTotalPriceInTheCart(), 116.9f);

    }
}
