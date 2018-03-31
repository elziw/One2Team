package com.selenium.test.junit.tests;
import com.selenium.test.junit.rules.ScreenShotOnFailRule;
import com.selenium.test.pages.CardPage;
import com.selenium.test.pages.LoginPage;
import com.selenium.test.pages.HomePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;


import java.util.*;

import static org.junit.Assert.assertTrue;


public class One2Team {

    @Rule
    public ScreenShotOnFailRule screenShotOnFailRule = new ScreenShotOnFailRule();

    @Before
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
    }

    @Test
    public void testSearch() {
        String login = "candidat";
        String password = "Candidat1*";
        String domain  = "telco";



        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.login(login, password, domain);
        CardPage cardPage = homePage.createCard();
        cardPage.chooseSlideBoard("Zz_Taches");
        cardPage.addCard(6);
        cardPage.dragAndDropCard();
        cardPage.uploadDocument("Tache11");




    }


    @After
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }
}
