package SanityTests;

import DataProviders.NewsDataProvider;
import InfraObjects.NewsObject;
import PageObjects.NewsPage;
import Utilities.CommonOptions;
import Utilities.NewsExporter;
import Verifications.Verifications;
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
    public void newsPageVerificationTests(String URL, NewsPage newsPage) {

        //Print URL
        System.out.println("Start Test for the next URL:" + URL);

        //Go to URL
        webDriver.get(URL);

        //Load newsObjectsList with pages data
        List<NewsObject> newsObjectsList = NewsObject.storeNewsInfo(newsPage.getListItem(), newsPage);

        //Verify articles expected data
        Verifications.verifyNewsData(newsObjectsList);

        //Add articles for print
        allNews.addAll(newsObjectsList);

        //Print
        NewsExporter.exportNews(allNews);

        //Assert all
        softAssert.assertAll();
    }

}
