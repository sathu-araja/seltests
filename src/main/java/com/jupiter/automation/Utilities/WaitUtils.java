package com.jupiter.automation.Utilities;

import org.openqa.selenium.WebDriver;

public class WaitUtils
{

    public WaitUtils() {
    }

    public static void waitFor(Long timeInMilliSeconds) throws InterruptedException {
        Thread.sleep(timeInMilliSeconds);
    }
}
