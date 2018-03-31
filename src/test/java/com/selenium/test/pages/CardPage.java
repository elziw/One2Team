package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import com.sun.glass.events.KeyEvent;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.JavascriptExecutor;
import com.paulhammant.ngwebdriver.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CardPage extends BasePage {

    public String cardName = generateCardName();

	@FindBy(css = "#s2id_autogen1")
	private WebElement slideboardListElement;

	@FindBy(css = "#s2id_autogen2_search")
	private WebElement slideboardInputText;

	@FindBy(xpath = "//div[@class= 'card__text__down ng-binding' and contains(text(), '1652246910')]")
    private WebElement cardToMoveElement;

	@FindBy(css = "#columns-container > div > div.containCol__col.column-animation.col1 > div.containCol__col__card")
	private WebElement destinationCardToMoveElement;

	@FindBy(css = "card-modal-block-dumb:nth-child(2)")
    private WebElement uploadDocElement;

	@FindBy(css = "div > div:nth-child(1) > internal-attachments-section > div > div.field-header > div.field-file-add > div > a")
	private WebElement addDocElement;




    public CardPage(){
        super(false);
    }

    @Override
    protected void openPage() {
        //do nothing
    }

    @Override
    public boolean isPageOpened() {
        try {



            WebDriverFactory.getDriver().switchTo().frame("slideboard");
            return slideboardListElement.isDisplayed();
        }   catch (NoSuchElementException e){
            return false;
        }
    }

    public void chooseSlideBoard (String option) {

        slideboardListElement.click();
        slideboardInputText.sendKeys(option);
        slideboardInputText.sendKeys(Keys.RETURN);






    }

    public String generateCardName  (){

        int t = ThreadLocalRandom.current().nextInt();
        String s = Integer.toString(t);
        return s;
    }

    public void addCard(int i ) throws StaleElementReferenceException{

/** Here adding try and catch in order to resolve the problem caused by Javascript deleting the icon from the DOM*/


        try {
            WebElement column = WebDriverFactory.getDriver().findElement(By.xpath("//*[@id=\"columns-container\"]/div/div["+i+"]"));
            Actions action = new Actions(WebDriverFactory.getDriver());
            action.moveToElement(column).moveToElement(WebDriverFactory.getDriver().findElement(By.xpath("//*[@id=\"columns-container\"]/div/div["+ i +"]/div[1]/div[2]/div[1]"))).click().build().perform();
            WebElement addCardIcon = WebDriverFactory.getDriver().findElement(By.xpath("//*[@id=\"add-card-input-"+(i-1)+"\"]"));

            addCardIcon.sendKeys(cardName);
            System.out.println("Card"+cardName+"has been added");
            addCardIcon.sendKeys(Keys.RETURN);



        }

        catch (org.openqa.selenium.StaleElementReferenceException ex) {

            WebElement column = WebDriverFactory.getDriver().findElement(By.xpath("//*[@id=\"columns-container\"]/div/div["+i+"]"));
            Actions action = new Actions(WebDriverFactory.getDriver());
            action.moveToElement(column).moveToElement(WebDriverFactory.getDriver().findElement(By.xpath("//*[@id=\"columns-container\"]/div/div["+ i +"]/div[1]/div[2]/div[1]"))).click().build().perform();
            WebElement addCardIcon = WebDriverFactory.getDriver().findElement(By.xpath("//*[@id=\"add-card-input-"+(i-1)+"\"]"));
            addCardIcon.sendKeys(cardName);
            System.out.println("Card"+cardName+"has been added");
            addCardIcon.sendKeys(Keys.RETURN);



            }






    }




    public void dragAndDropCard()

    {


        Actions builder = new Actions(WebDriverFactory.getDriver());

        WebDriverFactory.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Action dragAndDrop = builder.clickAndHold(WebDriverFactory.getDriver().findElement(By.xpath("//div[@class= 'card__text__down ng-binding' and contains(text(),'"+cardName+"' )]")))

                .moveToElement(destinationCardToMoveElement)
                .release(destinationCardToMoveElement)
                .build();

        dragAndDrop.perform();

        //Here need to run on debug mode because of a compability buh betweeen selelnium 3.11 and guava
        // Add wait untel expected conditions find element in moved column.

        FluentWait<ChromeDriver> fluentWait = new FluentWait<>(WebDriverFactory.getDriver())
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

//        fluentWait.until(WebDriverFactory.getDriver().findElement(By.cssSelector("#columns-container > div > div.containCol__col.column-animation.col"+d+" > div.containCol__col__card")));

//        Wait until element is moved to right column



    }



    public void uploadDocument( String taskName )

    {

        WebElement taskElement =  WebDriverFactory.getDriver().findElement(By.xpath("//*[@id=\"/ogp/2180120\"]"));
        WebDriverFactory.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        taskElement.click();
        uploadDocElement.click();
        addDocElement.click();
       File file = new File("/Users/ziedbejaoui/Desktop/one2team.PDF");
        try {

            Robot robot = new Robot () ;

            robot.mouseMove(500, 80);
            robot.delay(1500);
            robot.mousePress(InputEvent.META_MASK);
            robot.mouseRelease(InputEvent.META_MASK);
            robot.keyPress(InputEvent.META_MASK);

            //not sure this is the best solution maybe need to use an external tool such as AutoIT in windows




        }

        catch (java.awt.AWTException exc) {

            Assert.fail("Exception when creating object Robot"+ exc);
        }







    }


    }