package com.qa.pages;

import com.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchPage {
    private WebDriver driver;

    public  SearchPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

//    @FindBy(how = How.CSS,using=".autopop__wrap.makeFlex.column.defaultCursor")
//    private WebElement model_login;

    @FindBy(how = How.XPATH,using="//div[contains(@class,'loginModal displayBlock modalLogin dynHeight personal')]")
    private WebElement model_login;


    @FindBy(how = How.XPATH,using="//div[contains(@class,'fsw_inputBox searchCity inactiveWidget')]")
    private WebElement btn_formCity;

    @FindBy(how = How.XPATH,using="//input[@placeholder='From']")
    private WebElement txt_formCity;

    @FindBy(how=How.XPATH,using="//li[@id='react-autowhatever-1-section-0-item-0']")
    private WebElement li_City;

    @FindBy(how = How.XPATH,using="//div[contains(@class,'fsw_inputBox searchToCity inactiveWidget')]")
    private WebElement btn_toCity;

    @FindBy(how = How.XPATH,using="//input[@placeholder='To']")
    private WebElement txt_toCity;

    @FindBy(how = How.XPATH,using="//div[contains(@class,'fsw_inputBox dates inactiveWidget')]")
    private WebElement link_departure;

    @FindBy(how = How.CSS,using=".DayPicker-Caption>div")
    private WebElement div_month;

    @FindBy(how = How.CSS,using="span.DayPicker-NavButton.DayPicker-NavButton--next")
    private WebElement btn_next;

    @FindBy(how = How.XPATH,using="//div[@class='DayPicker-Months']/div[1]//div[contains(@class,'DayPicker-Day')]//p[1]")
    private List<WebElement> link_allDates;

    @FindBy(how = How.XPATH,using="//div[@class='DayPicker-Months']/div[1]//div[contains(@class,'DayPicker-Day')]")
    private List<WebElement> link_allDatesHotel;

    @FindBy(how = How.XPATH,using="//a[contains(text(),'Search')]")
    private WebElement link_search;

    @FindBy(how = How.XPATH,using="//span[text()='Hotels']//parent::a")
    private WebElement link_hotel;

    @FindBy(how = How.XPATH,using="//label[@for='city']//parent::div")
    private WebElement div_hotelSearchBox;

    @FindBy(how = How.XPATH,using="//input[@placeholder='Enter city/ Hotel/ Area/ Building']")
    private WebElement txt_hotelSearchBox;

    @FindBy(how = How.CSS,using="li#react-autowhatever-1-section-0-item-0")
    private WebElement list_hotelSearchBox;

    @FindBy(how = How.XPATH,using="//span[text()='Hotels']//parent::a")
    private WebElement div_checkIn;

    @FindBy(how = How.XPATH,using="//button[@id='hsw_search_button']")
    private WebElement btn_searchHotel;


    public SearchResultPage searchFlight(String fromCity, String toCity)  {
        try{
            try{
                if(model_login.isDisplayed()){
                    model_login.click();
                }
            }
            catch(NoSuchElementException e){
                e.printStackTrace();
            }

            TestBase.testBase.uiOperator.click(btn_formCity,"fromCity");
            TestBase.testBase.uiOperator.clear(txt_formCity);
            TestBase.testBase.uiOperator.enterText(txt_formCity,"FromCityTxtBox",fromCity);
            WebDriverWait wait =new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(li_City));
            Thread.sleep(2000);
            TestBase.testBase.uiOperator.click(li_City,"CityList");
            TestBase.testBase.uiOperator.takeScreenshot();
            TestBase.testBase.uiOperator.click(btn_toCity,"toCity");
            TestBase.testBase.uiOperator.clear(txt_toCity);
            TestBase.testBase.uiOperator.enterText(txt_toCity,"toCityTxtBox",toCity);
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(li_City));
            TestBase.testBase.uiOperator.click(li_City,"CityList");

            while(true){
                if(div_month.getText().equals("March 2021"))
                    break;
                else
                    btn_next.click();
            }
            for(WebElement ele:link_allDates){
                if(ele.getText().equals("10")){
                    ele.click();
                    break;
                }
            }
            TestBase.testBase.uiOperator.click(link_search,"SearchBtn");
            return new SearchResultPage(driver);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void searchHotel(String city){
        try{
            try{
                if(model_login.isDisplayed()){
                    model_login.click();
                }
            }
            catch(NoSuchElementException e){
                e.printStackTrace();
            }
            TestBase.testBase.uiOperator.click(link_hotel,"hotelLink");

            WebDriverWait wait =new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(div_hotelSearchBox));

            TestBase.testBase.uiOperator.click(div_hotelSearchBox,"hotelSearchBox");
            TestBase.testBase.uiOperator.clear(txt_hotelSearchBox);
            TestBase.testBase.uiOperator.enterText(txt_hotelSearchBox,"hotelSearchBox",city);
            Thread.sleep(2000);
            TestBase.testBase.uiOperator.click(list_hotelSearchBox,"listHotelSearchBox");
            div_checkIn.click();
            String strMonthYr=div_month.getText();
            System.out.println("Hotel MonthYear::"+strMonthYr);
            while(true){
                if(div_month.getText().equals("January2021"))
                break;
                else
                    btn_next.click();
            }
            for(WebElement ele:link_allDatesHotel){
//                System.out.println(ele.getText());
                if(ele.getText().equals("10")){
                    ele.click();
                    break;
                }
            }
            for(WebElement ele:link_allDatesHotel){
//                System.out.println(ele.getText());
                if(ele.getText().equals("15")){
                    ele.click();
                    break;
                }
            }
            TestBase.testBase.uiOperator.click(btn_searchHotel,"hotelSearchBtn");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
