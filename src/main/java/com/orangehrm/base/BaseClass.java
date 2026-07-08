package com.orangehrm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class BaseClass {

    protected static Properties prop;
    protected WebDriver driver;

    @BeforeSuite
    // Load the configuration properties file
    public void loadConfig() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        prop.load(fis);
    }

    @BeforeMethod
    public void setup() throws IOException {
        System.out.println("Setting of WebDriver: " +this.getClass().getSimpleName());
        launchBrowser();
        configureBrowser();
        staticWait(2);
    }

    /*
    Initialize the WebDriver based on the
    browser specified in the config properties file
    */
    public void launchBrowser() throws IOException {
        String browser = prop.getProperty("browser");
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        }else if (browser.equals("edge")) {
            driver = new EdgeDriver();
        }else{
            throw new IOException("Invalid Browser Name (Try chrome, firefox, or edge): " + browser);
        }
    }

    //Configure browser settings
    private void configureBrowser() {
        //Implicit Wait
        int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(implicitWait));

        //Maximize the window
        driver.manage().window().maximize();

        //Navigate to URL
        try {
            driver.get(prop.getProperty("url"));
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

    // Getter method for prop
    public static Properties getProp() {
        return prop;
    }

    //Driver getter method
    public WebDriver getDriver() {
        return driver;
    }

    //Driver setter method
    public WebDriver setDriver(WebDriver driver) {
        this.driver = driver;
        return driver;
    }

    public void staticWait(int seconds) {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
    }
}
