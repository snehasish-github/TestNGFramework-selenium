package com.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;



public class WebDriverManager {


    public static WebDriver initializeWebDriver(){
        WebDriver driver=null;
        String browserName=TestBase.testBase.readProperties("Browser");

            if(browserName.equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver","C:\\Users\\sneha\\Downloads\\chromedriver_win32\\chromedriver.exe");
                driver=new ChromeDriver();

            }
            else if(browserName.equalsIgnoreCase("firefox")){
                System.setProperty("webdriver.gecko.driver","D:\\Software\\geckodriver.exe");
                driver=new FirefoxDriver();
            }
            return driver;
    }
}
