package Utilities;

import PageObjects.AppleNewsRoomPage;
import PageObjects.CovidSummaryPage;
import PageObjects.WhoNewsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.ZoneId;

public class Base {

    public static WebDriver webDriver;
    public static WebDriverManager webDriverManager;
    public static WebDriverWait wait;
    public static Actions actions;
    public static SoftAssert softAssert;
    public static JavascriptExecutor js;

    public static PageManager pageManager;
    public static WhoNewsPage whoNewsPage;
    public static AppleNewsRoomPage appleNewsRoomPage;
    public static CovidSummaryPage covidSummaryPage;

    public static ZoneId zone = ZoneId.of("Asia/Jerusalem");

}
