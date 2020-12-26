package com.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.base.TestBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNGListernsImp implements ITestListener {
    public static ExtentReports extent;
    public ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extent.createTest(result.getMethod().getMethodName());
        TestBase.testBase.extentTest.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

        TestBase.testBase.extentTest.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        try{
            System.out.println("On Start");
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
            Date date = new Date();
            String strDay = format.format(date);
            String path = System.getProperty("user.dir") + "//src//test//TestReport//report_" + strDay + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            spark.config().setDocumentTitle("TestReport");
            spark.config().setReportName("TestReportName");
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("HostName", "LocalHost");
            extent.setSystemInfo("OS", "Windows10");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();

    }
}
