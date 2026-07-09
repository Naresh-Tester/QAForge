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
import org.apache.logging.log4j.Logger;
import com.orangehrm.utilities.LoggerUtil;

public class BaseClass {

    protected static WebDriver driver;
    private static final Logger logger = LoggerUtil.getLogger(BaseClass.class);

    @BeforeMethod
    public void setup() {
        logger.info("Setting up WebDriver for {}", this.getClass().getSimpleName());
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
            logger.info("Chrome browser launched");
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
            logger.info("Firefox browser launched");
        }else if (browser.equals("edge")) {
            driver = new EdgeDriver();
            logger.info("Edge browser launched");
        }else{
            logger.error("Invalid browser name: {}", browser);
            throw new RuntimeException("Invalid Browser Name (Try chrome, firefox, or edge): " + browser);
        }
    }

    //Configure browser settings
    private void configureBrowser() {
        //Implicit Wait
        int implicitWait = ConfigReader.getImplicitWait();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(implicitWait));
        logger.info("Implicit wait set to {} seconds", implicitWait);

        //Maximize the window
        driver.manage().window().maximize();
        logger.info("Browser window maximized");

        //Navigate to URL
        try {
            driver.get(ConfigReader.getUrl());
            logger.info("Navigated to URL: {}", ConfigReader.getUrl());
        }catch (Exception e){
            logger.error("Failed to navigate to URL: {}", e.getMessage());
        }
    }

    @AfterMethod
    //Close all browsers and end the session
    public void teardown() throws IOException {
        if (driver != null) {
            try {
                logger.info("Closing browser");
                driver.quit();
                logger.info("Browser closed successfully");
            } catch (Exception e) {
                logger.error("Failed to close browser: {}", e.getMessage());
            }
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
