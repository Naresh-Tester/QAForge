package com.orangehrm.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtil {

    // Capture screenshot and save it to target/screenshots
    public static String captureScreenshot(WebDriver driver, String testName) {

        String folderPath = "target/screenshots";

        try {
            // Create screenshots directory if it doesn't exist
            Files.createDirectories(Paths.get(folderPath));

            // Capture screenshot
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Generate unique screenshot name
            String fileName = testName + "_" + System.currentTimeMillis() + ".png";

            // Destination path
            Path destination = Paths.get(folderPath, fileName);

            // Copy screenshot to destination
            Files.copy(source.toPath(), destination);

            return destination.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}