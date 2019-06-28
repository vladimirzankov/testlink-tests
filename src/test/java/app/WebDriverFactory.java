package app;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import static tests.TestBase.logger;

public class WebDriverFactory {

    public static WebDriver createNew(String browserName) {

        Browser browser = defineBrowserType(browserName);

        switch(browser) {
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                return new EdgeDriver(edgeOptions);
            default:
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                //chromeOptions.addArguments("--window-position=2000,0");
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                //firefoxOptions.addArguments("--window-position=2000,0");
                return new FirefoxDriver();
        }
    }

    public static WebDriver createNew(String browserName, MutableCapabilities options) {

        Browser browser = defineBrowserType(browserName);

        switch(browser) {
            case EDGE:
                return new EdgeDriver((EdgeOptions)options);
            default:
            case CHROME:
                return new ChromeDriver((ChromeOptions)options);
            case FIREFOX:
                return new FirefoxDriver((FirefoxOptions)options);
        }
    }

    public static Browser defineBrowserType(String browserName) {
        Browser browser;
        try {
            browser = browserName != null && !browserName.isEmpty() ? Browser.valueOf(browserName.toUpperCase()) : Browser.CHROME;
        }
        catch(IllegalArgumentException e) {
            browser = Browser.CHROME;
            logger.warn("Unsupported browser \"" + browserName + "\". Test runs with chrome" );
        }
        return browser;
    }

    public enum Browser {
        CHROME,
        EDGE,
        FIREFOX
    }

}
