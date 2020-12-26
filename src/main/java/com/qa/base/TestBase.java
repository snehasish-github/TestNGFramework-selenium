package com.qa.base;

import com.aventstack.extentreports.ExtentTest;
import com.qa.utils.TestDataUtil;
import com.qa.utils.TestNGListernsImp;
import com.qa.utils.UiOperator;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {


    public static TestBase testBase;
    private Properties properties;
    public ThreadLocal<WebDriver> driver=new ThreadLocal<>();
    public ThreadLocal<ExtentTest> extentTest=new ThreadLocal<>();
    public UiOperator uiOperator;



    private TestBase(){
        try{
            FileInputStream fi=new FileInputStream(new File(System.getProperty("user.dir")
                    +File.separator+"config.properties"));
            properties=new Properties();
            properties.load(fi);
        }
        catch(IOException e){
           e.printStackTrace();
        }
    }

    public static void initialize(){
        if(testBase==null){
            System.out.println(" Test Base Thread Name: "+Thread.currentThread().getName()+" Thread Id: "+Thread.currentThread().getId());
            testBase=new TestBase();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestBase.testBase.driver.set(WebDriverManager.initializeWebDriver());
        testBase.uiOperator=new UiOperator();
    }

    public String readProperties(String propertyName){
        return properties.getProperty(propertyName);
    }



}
