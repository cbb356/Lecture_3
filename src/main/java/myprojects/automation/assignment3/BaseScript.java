package myprojects.automation.assignment3;

import myprojects.automation.assignment3.utils.EventHandler;
import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {

    /**
     *
     * @return New instance of {@link WebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    public static WebDriver getDriver() {
        String browser = Properties.getBrowser();
        switch (browser) {
            case "firefox":
                System.setProperty(
                        "webdriver.gecko.driver",
                        new File(BaseScript.class.getResource("/geckodriver.exe").getFile()).getPath());
                return new FirefoxDriver();
            case "ie":
            case "internet explorer":
            case "iexplore":
                System.setProperty(
                        "webdriver.ie.driver",
                        new File(BaseScript.class.getResource("/IEDriverServer.exe").getFile()).getPath());
                return new InternetExplorerDriver();
            case "chrome":
            default:
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(BaseScript.class.getResource("/chromedriver.exe").getFile()).getPath());
                return new ChromeDriver();
        }
    }

    /**
     * Creates {@link WebDriver} instance with timeout and browser window configurations.
     *
     * @return New instance of {@link EventFiringWebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    public static EventFiringWebDriver getConfiguredDriver() {
        EventFiringWebDriver driver = new EventFiringWebDriver(getDriver());
        driver.manage().window().maximize();

        driver.register(new EventHandler());

        return driver;
    }

    public static void quitDriver(WebDriver driver){
        driver.quit();
    }
}
