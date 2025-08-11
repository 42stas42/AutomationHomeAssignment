package Utilities;

import PageObjects.AppleNewsRoomPage;
import PageObjects.CovidSummaryPage;
import PageObjects.WhoNewsPage;
import org.openqa.selenium.support.PageFactory;

public class PageManager extends Base{

    static {

        whoNewsPage = PageFactory.initElements(webDriver, WhoNewsPage.class);
        appleNewsRoomPage = PageFactory.initElements(webDriver, AppleNewsRoomPage.class);
        covidSummaryPage = PageFactory.initElements(webDriver, CovidSummaryPage.class);

    }

}
