package com.qa.testCases;

import com.aventstack.extentreports.Status;
import com.qa.base.TestBase;
import com.qa.pages.SearchPage;
import com.qa.pages.SearchResultPage;
import com.qa.utils.TestDataUtil;
import org.testng.*;
import org.testng.annotations.*;

public class LogInPageTest {
    SearchPage searchPage;
    SearchResultPage searchResultPage;
//    ExtentReports extent = ExtentReportUtil.ExtentReportSetup();
//    ExtentTest extentTest;

//    @BeforeSuite
//    public void beforeSuite(){
//        System.out.println("before suite");
//
//    }
//    @BeforeClass
//    public void beforeClass(){
//        System.out.println("before Class");
//
//    }

    @BeforeMethod
    public void setup() {
        try{
            System.out.println("Thread Name: "+Thread.currentThread().getName()+" Thread Id: "+Thread.currentThread().getId());
            System.out.println("before Method");
            TestBase.initialize();
            TestBase.testBase.driver.get().manage().deleteAllCookies();
            TestBase.testBase.driver.get().manage().window().maximize();
            TestBase.testBase.driver.get().get(TestBase.testBase.readProperties("url"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @DataProvider()
    public Object[][] getTestData( final ITestNGMethod testNGMethod) {
        System.out.println("Dtata prvider");
        return TestDataUtil.readTestData("Search",testNGMethod.getMethodName());
    }

    @Test(dataProvider = "getTestData")
    public void flightSearch(String TC_name,String fromCity, String toCity, String fromDate, String toDate, String tripType) {

        try {
            searchPage = new SearchPage(TestBase.testBase.driver.get());
            searchResultPage = searchPage.searchFlight(fromCity, toCity);
            searchResultPage.waitForSearchResultPage();

            if (searchResultPage.getTriptype().equals("One Way")) {
                TestBase.testBase.extentTest.get().log(Status.PASS, "Expected");
            } else
                TestBase.testBase.extentTest.get().log(Status.FAIL, "Failed");
        } catch (Exception e) {
            e.printStackTrace();
            TestBase.testBase.extentTest.get().log(Status.FAIL, e.getMessage());
        }
    }

    @Test(dataProvider = "getTestData")
    public void hotelSearch(String TC_name,String fromCity, String toCity, String fromDate, String toDate, String tripType) {
        try {
            searchPage = new SearchPage(TestBase.testBase.driver.get());
            searchPage.searchHotel(fromCity);
            TestBase.testBase.extentTest.get().fail("Failed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        TestBase.testBase.driver.get().close();
        TestBase.testBase.driver.remove();

    }
}
