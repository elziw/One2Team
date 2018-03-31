package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

    private static final String PAGE_URL = "http://chewie.one2team.com/";

	@FindBy(css = "#bigLogo")
	private WebElement searchStringElement;


    @FindBy(css = "#userName")
	private WebElement loginElement;

	@FindBy(css = "#passWord")
	private WebElement passwordElement;

	@FindBy(css = "#domainName")
	private WebElement domainnameElement;

	@FindBy(css = "#flogin > input[type=\"submit\"]")
	private WebElement connexionElement;





    public LoginPage() {
        super(true);
    }

    @Override

    protected void openPage() {

        getDriver().manage().deleteAllCookies();
        getDriver().get(PAGE_URL);

    }

    @Override
    public boolean isPageOpened() {
        return searchStringElement.isDisplayed();
    }

    public void insertLogin(String login) {

    }
    public void insertPassword(String password) {
        passwordElement.sendKeys(password);
    }
    public void insertDomainName(String domainName) {

    }

    public HomePage login (String login, String password, String domain)
    {

        loginElement.clear();
        passwordElement.clear();
        domainnameElement.clear();
        loginElement.sendKeys(login);
        passwordElement.sendKeys(password);
        domainnameElement.sendKeys(domain);
        connexionElement.click();
        return new HomePage();

    }



}



