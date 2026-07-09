package com.orangehrm.listeners;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ScreenshotUtil;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());

        // Save screenshot to target/screenshots
        ScreenshotUtil.captureScreenshot(BaseClass.getDriver(), result.getName());

        // Attach screenshot to Allure
        takeScreenshot();
    }

    private void takeScreenshot() {
        if (BaseClass.getDriver() != null) {
            byte[] screenshot = ((TakesScreenshot) BaseClass.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
        }
    }
}