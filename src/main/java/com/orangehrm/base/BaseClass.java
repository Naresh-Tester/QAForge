package com.orangehrm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import com.orangehrm.utilities.ConfigReader;

public class BaseClass {

    protected static WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.out.println("Setting of WebDriver: " +this.getClass().getSimpleName());
        launchBrowser();
        configureBrowser();
        staticWait(2);
    }

    /*
    Initialize the WebDriver based on the
    browser specified in the config properties file
    */
    public void launchBrowser() {
        String browser = ConfigReader.getBrowser();
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        }else if (browser.equals("edge")) {
            driver = new EdgeDriver();
        }else{
            throw new RuntimeException("Invalid Browser Name (Try chrome, firefox, or edge): " + browser);
        }
    }

    //Configure browser settings
    private void configureBrowser() {
        //Implicit Wait
        int implicitWait = ConfigReader.getImplicitWait();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(implicitWait));

        //Maximize the window
        driver.manage().window().maximize();

        //Navigate to URL
        try {
            driver.get(ConfigReader.getUrl());
        }catch (Exception e){
            System.out.println("Failed to navigate the URL: " + e.getMessage());
        }
    }

    @AfterMethod
    //Close all browsers and end the session
    public void teardown() throws IOException {
        if (driver != null) {
            try{
            driver.quit();
        }catch (Exception e){
            System.out.println("Failed to quit driver: " + e.getMessage());}
        }
    }

    //Driver getter method
    public static WebDriver getDriver() {
        return driver;
    }

    //Pause the execution for the specified number of seconds
    public void staticWait(int seconds) {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
    }
}
