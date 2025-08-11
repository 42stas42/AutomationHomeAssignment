package SanityTests;

import DataProviders.NewsDataProvider;
import InfraObjects.NewsObject;
import PageObjects.NewsPage;
import Utilities.CommonOptions;
import Utilities.NewsExporter;
import Verifications.Verifications;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class NewsPageTests extends CommonOptions {

    @AfterClass
    public void exportAllNews() {
        NewsExporter.exportNews(allNews); // now has both sites’ data
    }

    List<NewsObject> allNews = new ArrayList<>();

    @Test(dataProvider = "newsDataProvider", dataProviderClass = NewsDataProvider.class)
    @Description("Go to WHO and APPLE sites --> Collect Articles by parameters --> Verify")
    public void newsPageVerificationTests(String URL, NewsPage newsPage) {

        //Go to URL
        Allure.step("Go to URL");
        webDriver.get(URL);

        //Load newsObjectsList with pages data
        Allure.step("Load newsObjectsList with pages data");
        List<NewsObject> newsObjectsList = NewsObject.storeNewsInfo(newsPage.getListItem(), newsPage);

        //Verify articles expected data
        Allure.step("Verify articles expected data");
        Verifications.verifyNewsData(newsObjectsList);

        //Add articles for print
        Allure.step("Add articles for print");
        allNews.addAll(newsObjectsList);

        //Print
        NewsExporter.exportNews(allNews);

        //Assert all
        Allure.step("Assert all");
        softAssert.assertAll();
    }

}
