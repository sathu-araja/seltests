package com.jupiter.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase
{
    public WebDriver driver;
    public TestBase(){
    }

    Properties properties = new Properties();

    public WebDriver testInitializer() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\GlobalData.properties");
        properties.load(fileInputStream);
        String browserName = properties.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().proxy("http://proxy.xxxx.xxxxx.xx.xx:9999/:80").setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--window-size=1920,1080", "--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeTest
    public WebDriver launchJupiterApp()
            throws IOException
    {
        testInitializer();
        driver.navigate().to("http://jupiter.cloud.planittesting.com");
        return driver;
    }


    @AfterTest
    public void tearDown()
    {
        driver.close();
    }


    public String captureScreenshot(String testName, WebDriver driver)
            throws IOException
    {
        TakesScreenshot takesScreenshot = ((TakesScreenshot)driver);
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//"+testName+".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir")+"//reports//"+testName+".png";

    }

}
