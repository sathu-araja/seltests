package com.jupiter.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class TestBase
{
    public WebDriver driver;
    public TestBase(){
    }

    Properties properties = new Properties();

    public WebDriver testInitializer() 
        throws IOException 
    {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\GlobalData.properties");
        properties.load(fileInputStream);
        String browserName = properties.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome"))
        {
            //to run on local macine use this driver setup code
            /*the below webdriver manager can be used to set proxy
            for webdriver manager to be able to download driver*/

            //WebDriverManager.chromedriver().proxy("http://xxxx.xxxx.xxxx.co.nz:0000/:80").setup();
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--window-size=1920,1080", "--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);

            //To Run on remote machine use below block of code
//            ChromeOptions options = new ChromeOptions();
//            options.setAcceptInsecureCerts(true);
//
//            options.setCapability("build", "Jupier 1.0");
//            options.setCapability("name", "Testing Jupiter");
//            options.setCapability("platformName", "Windows 10");
//            options.setCapability("browserName", "Chrome");
//            options.setCapability("browserVersion", "latest");
//
//            try {
//                driver = new RemoteWebDriver(new URL("http://xxx.xxx.x.xxx:4444"), ((Capabilities) options));
//            } catch (MalformedURLException e) {
//                System.out.println("Invalid grid URL");
//            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public WebDriver launchJupiterApp()
            throws IOException
    {
        testInitializer();
        driver.navigate().to("http://jupiter.cloud.planittesting.com");
        return driver;
    }
    
    @AfterMethod(alwaysRun = true)
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
