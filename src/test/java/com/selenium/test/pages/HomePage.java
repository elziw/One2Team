package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends BasePage {


    @FindBy()
    private WebElement searchStringElement;

    @FindBy(css="body > div.GN-FVITDKI > div.GN-FVITDII.GN-FVITDJI > table > tbody > tr > td:nth-child(1) > input")
    private WebElement contentElement;

	@FindBy(css = "#social_avatar")
	private WebElement loginelement;

	@FindBy(css = "html > frameset > frame:nth-child(1)")
	private WebElement VerticaloptionsFrame;


	@FindBy(id = "xrn:database:ondb/table/82_anonymous")
	private WebElement slideBoardElement;

	@FindBy(css = "#img_slideboard")
	private WebElement slideBoardIcon;





    public HomePage(){
        super(false);
    }

//    public HomePage(boolean openPageByUrl) {
//        super(openPageByUrl);
//    }


    @Override
    protected void openPage() {
        //do nothing
    }

    @Override
    public boolean isPageOpened() {
        try {


            return contentElement.isDisplayed();
        }   catch (NoSuchElementException e){
            return false;
        }
    }

    public void selectSlideBoard () {

        WebDriverFactory.getDriver().switchTo().frame("frame-object");
        int size = WebDriverFactory.getDriver().findElements(By.tagName("frame")).size();
        System.out.println("total number of frames"+ size);
        WebDriverFactory.getDriver().switchTo().frame(1);
        WebDriverFactory.getDriver().switchTo().frame(0);
        slideBoardElement.click();

    }

    public CardPage createCard () {


        slideBoardIcon.click();
        return new CardPage ();

    }



    }




