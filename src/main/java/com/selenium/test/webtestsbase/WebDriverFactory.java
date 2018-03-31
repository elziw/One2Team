package com.selenium.test.webtestsbase;

import com.selenium.test.configuration.TestsConfig;
import com.sun.tools.doclets.internal.toolkit.util.DocFinder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.io.*;
import java.nio.*;



import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sidelnikov Mikhail on 18.09.14.
 * Update by Zied Bejaoui on March 2018 for selenium webdriver 3.11
 * Base class for web tests. It contains web driver {@link org.openqa.selenium.WebDriver} instance, used in all tests.
 * All communications with driver should be done through this class
 */
public class WebDriverFactory {
    private static final long IMPLICIT_WAIT_TIMEOUT = 5;
    private static ChromeDriver driver;





    /**
     * Getting of pre-configured {@link org.openqa.selenium.WebDriver} instance.
     * Please use this method only after call {@link #startBrowser(boolean) startBrowser} method
     *
     * @return webdriver object, or throw IllegalStateException, if driver has not been initialized
     */
    public static ChromeDriver getDriver() {
        if (driver != null) {
            return driver;
        } else {
            throw new IllegalStateException("Driver has not been initialized. " +
                    "Please call WebDriverFactory.startBrowser() before use this method");
        }
    }


    /**
     * Main method of class - it initialize driver and starts browser.
     *
     * @param isLocal - is tests will be started local or not
     */
    public static void startBrowser(boolean isLocal) {
        if (driver == null) {
            Browser browser = TestsConfig.getConfig().getBrowser();
            if (!isLocal) {
                driver = new ChromeDriver(CapabilitiesGenerator.getDefaultCapabilities(browser));
            } else {
                switch (browser) {
                    case CHROME:
                        ChromeOptions options = new ChromeOptions();
                        options.setCapability("webdriver.chrome.driver", "/Users/ziedbejaoui/IdeaProjects/One2Team/Drivers/chromedriver");
                        driver = new ChromeDriver(options);
                        driver.manage().deleteAllCookies();
                        break;
                    default:
                        throw new IllegalStateException("Unsupported browser type");
                }
            }
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        } else {
            throw new IllegalStateException("Driver has already been initialized. Quit it before using this method");
        }
    }

    /**
     * Finishes browser
     */
    public static void finishBrowser() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            driver = null;
        }
    }

    /**
     * Method for screenshot taking. It is empty now, because you could save your screenshot as you want.
     * This method calls in tests listeners on test fail
     */
    public static void takeScreenShot()  {

        /*try
        {
        Path srcPath=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE).toPath();
        Path targetPath = Paths.get("/Users/ziedbejaoui/IdeaProjects/One2Team/target/Screenshots");
        Files.copy(srcPath, targetPath);
        }
        catch  (IOException e)
        {
            System.out.println("Expection" + e);
        }*/





    }

}
