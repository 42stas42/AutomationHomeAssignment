package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CommonOptions extends Base {

    @BeforeSuite
    public void beforeClass() throws Exception {
        startSession();

    }

    @AfterSuite
    public void afterClass() {
        webDriver.quit();
    }

    public void startSession() throws Exception {

        switch (ConfigReader.getProperty("PLATFORM")) {
            case "web":
                initBrowser(ConfigReader.getProperty("BROWSER"));
                initApi();
                break;
            case "api":
                initApi();
                break;
            case "mobile":
                initMobile(ConfigReader.getProperty("MOBILE_PLATFORM"));
                break;
            default:
                throw new Exception("Wrong platform name provided");
        }

        pageManager = new PageManager();
    }

    public void initBrowser(String browserType) throws Exception {
        switch (browserType) {
            case "chrome":
                initChrome();
                break;
            case "firefox":
                initFF();
                break;
            case "edge":
                initIE();
                break;
            default:
                throw new Exception("Wrong browswer name provided");
        }

        webDriver.manage().window().maximize();
        webDriver.get(ConfigReader.getProperty("URL_1"));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getIntProperty("IMPLICIT_WAIT_TIMEOUT")));
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigReader.getIntProperty("EXPLICIT_WAIT_TIMEOUT")));
        actions = new Actions(webDriver);
        softAssert = new SoftAssert();
        js = (JavascriptExecutor) webDriver;

    }

    public void initApi() {
    }

    public void initMobile(String mobilePlatform) {

    }

    void initChrome() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    void initFF() {
    }

    void initIE() {
    }

}
