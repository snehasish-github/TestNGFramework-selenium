package com.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;


public class WebDriverManager {


    public static WebDriver initializeWebDriver() {
        WebDriver driver = null;
        String browserName = TestBase.testBase.readProperties("Browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                    File.separator + "src" + File.separator + "test" +
                    File.separator + "Resources" + File.separator + "chromedriver.exe");
            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                    File.separator + "src" + File.separator + "test" +
                    File.separator + "Resources" + File.separator + "geckodriver.exe");

            driver = new FirefoxDriver();
        }
        return driver;
    }
}
