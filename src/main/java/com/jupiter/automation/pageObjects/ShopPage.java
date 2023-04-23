package com.jupiter.automation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
public class ShopPage
{
    public WebDriver driver;
    public ShopPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public List<WebElement> getAllItemsForSale(){
        List<WebElement> shopItems;
        return shopItems = driver.findElements(By.xpath("//li[@class='product ng-scope']"));
    }

    public WebElement getShopItemByName(String itemName)
    {
        return getAllItemsForSale().stream().filter(item->
                item.findElement(By.cssSelector("h4")).getAttribute("innerText").equals(itemName)).findFirst().orElse(null);
    }

    public WebElement getItemsBuyBtn(String itemName)
    {
        System.out.println("ITEM NAME ADDED TO CART IS :  " +getShopItemByName(itemName).getAttribute("innerText"));
        return getShopItemByName(itemName).findElement(By.cssSelector("li[class='product ng-scope'] a"));
    }

    public void buyItemByNameAndQuantityNeeded(String itemName, int qtyNeeded)
    {
        int i = 0;
        while(i!=qtyNeeded)
        {
            getItemsBuyBtn(itemName).click();
            i++;
        }
    }

}
