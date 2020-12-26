package com.qa.utils;

import com.aventstack.extentreports.Status;
import com.qa.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UiOperator  {


    public void click(WebElement webElement, String elementName){
        try{
            webElement.click();
            TestBase.testBase.extentTest.get().log(Status.PASS,"Element: "+elementName+" clicked");
        }
        catch(Exception e){
            e.printStackTrace();
            TestBase.testBase.extentTest.get().log(Status.FAIL,"Element: "+elementName+" not clicked due to "+e.getMessage());
        }
    }

    public void clear(WebElement webElement){
        try{
            webElement.clear();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void enterText(WebElement webElement, String elementName,String text){
        try{
            webElement.sendKeys(text);
            TestBase.testBase.extentTest.get().log(Status.PASS,"Entered Text "+"\""+text+"\""+"in "+elementName);
        }
        catch(Exception e){
            e.printStackTrace();
            TestBase.testBase.extentTest.get().log(Status.FAIL,"Element: "+elementName+" not clicked due to "+e.getMessage());
        }
    }
    public void takeScreenshot(){
        try{
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
            Date date = new Date();
            String strDay = format.format(date);
            String path = System.getProperty("user.dir") + "//src//test//TestReport//Screenshots//report_" + strDay + ".jpeg";
            File src = ((TakesScreenshot) (TestBase.testBase.driver.get())).getScreenshotAs(OutputType.FILE);
            File dst = new File(path);
            FileUtils.copyFile(src, dst);
            TestBase.testBase.extentTest.get().addScreenCaptureFromPath(path, "screenCapture");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}
