package com.orangehrm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    protected Properties prop;
    protected WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {

        //Load the config properties file
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        prop.load(fis);

        // Initialize the WebDriver based on the browser specified in the config properties file
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

        //Implicit Wait
        int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(implicitWait));

        //Maximize the window
        driver.manage().window().maximize();

        //Navigate to URL
        driver.get(prop.getProperty("url"));
    }

    @AfterMethod
    //Close all browsers and end the session
    public void teardown() throws IOException {
        if (driver != null) {
            driver.quit();
        }
    }
}
