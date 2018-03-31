package com.selenium.test.testng.tests;

import com.selenium.test.junit.rules.ScreenShotOnFailRule;
import com.selenium.test.pages.CardPage;
import com.selenium.test.pages.HomePage;
import com.selenium.test.pages.LoginPage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


public class One2Team {



    @BeforeTest
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
    }

    @Test
    public void testOne2Team() {
        String login = "candidat";
        String password = "Candidat1*";
        String domain  = "telco";



        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.login(login, password, domain);
        CardPage cardPage = homePage.createCard();
        cardPage.chooseSlideBoard("Zz_Taches");
        cardPage.addCard(3);
        cardPage.dragAndDropCard();
        cardPage.uploadDocument("Tache11");




    }


    @AfterTest
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }
}
