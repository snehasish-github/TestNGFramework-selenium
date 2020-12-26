package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {

    private WebDriver driver;

    public  SearchResultPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.XPATH,using="//p[contains(.,'Flights from')]")
    private WebElement p_flightsFrom;

    @FindBy(how = How.XPATH,using="//label[contains(.,'TRIP TYPE')]//parent::div//div[@class='multiDropDownVal']")
    private WebElement tripType;

    @FindBy(how = How.CSS,using="#fromCity")
    private WebElement fromCity;

    @FindBy(how = How.CSS,using="#toCity")
    private WebElement toCity;

    public String getTriptype(){
        try{
            return tripType.getText();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void waitForSearchResultPage(){

        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(p_flightsFrom));

    }
    public String getFromCity(){
        try{
            return fromCity.getAttribute("value");
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public String getToCity(){
        try{
            return toCity.getAttribute("value");
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
