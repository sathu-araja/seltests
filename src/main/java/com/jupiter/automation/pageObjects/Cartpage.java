package com.jupiter.automation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Cartpage
{
    public WebDriver driver;

    public Cartpage(WebDriver driver)
    {
        this.driver = driver;
    }

    public List<WebElement> getAllItemsInCartGrid(){
        List<WebElement> shopItems;
        return shopItems = driver.findElements(By.xpath("//tr[@class='cart-item ng-scope']"));
    }

    public WebElement getItemInTheCartByName(String itemName)
    {
        return getAllItemsInCartGrid().stream().filter(item->
                item.findElement(By.cssSelector("tr[class='cart-item ng-scope'] td:nth-child(1)")).getAttribute("innerText").equals(itemName)).findFirst().orElse(null);
    }

    public String getItemPriceInTheCartByName(String itemName)
    {
            System.out.println("ITEM NAME AND PRICE INSIDE THE CART IS :  " +getItemInTheCartByName(itemName).getAttribute("innerText"));
        return getItemInTheCartByName(itemName).findElement(By.cssSelector("tr[class='cart-item ng-scope'] td:nth-child(2)")).getAttribute("innerText");
    }

    public Float getSubTotalItemPriceInTheCartByName(String itemName)
    {
        String itemSubTotal = getItemInTheCartByName(itemName).findElement(By.cssSelector("tr[class='cart-item ng-scope'] td:nth-child(4)")).getAttribute("innerText");
        String removeStringInItemSubTotal = itemSubTotal.substring(1);
        Float numericValueOfItemSubTotal = Float.parseFloat(removeStringInItemSubTotal);
        return numericValueOfItemSubTotal;
    }

    public Float getTotalPriceInTheCart()
    {

        String itemSubTotal = driver.findElement(By.xpath("//tfoot/tr[1]/td[1]/strong")).getAttribute("innerText");
        String removeStringInTotalPrice = itemSubTotal.substring(7);
        Float numericValueOfTotalPrice= Float.parseFloat(removeStringInTotalPrice);
        return numericValueOfTotalPrice;
    }
}
