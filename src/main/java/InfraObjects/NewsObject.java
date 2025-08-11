package InfraObjects;

import Extentions.WebActions;
import PageObjects.NewsPage;
import Utilities.CommonOptions;
import Utilities.DateUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class NewsObject extends CommonOptions {

    String origin;
    String date;
    String type;
    String title;
    String newsUrl;
    String absoluteConvertedDate;

    public NewsObject(String origin, String date, String type, String title, String newsUrl, String absoluteConvertedDate) {
        this.origin = origin;
        this.date = date;
        this.type = type;
        this.title = title;
        this.newsUrl = newsUrl;
        this.absoluteConvertedDate = absoluteConvertedDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getAbsoluteConvertedDate() {
        return absoluteConvertedDate;
    }

    public void setAbsoluteConvertedDate(String absoluteConvertedDate) {
        this.absoluteConvertedDate = absoluteConvertedDate;
    }

    @Step("RUN: storeNewsInfo method")
    public static List<NewsObject> storeNewsInfo(List<WebElement> webElementList, NewsPage newsPage) {
        List<NewsObject> newsObjectsList = new ArrayList<>();

        try {
            Allure.step("START Method storeNewsInfo");
            Allure.step("Processing up to 5 articles from the provided list of " + webElementList.size() + " elements.");

            for (int i = 0; i < 5 && i < webElementList.size(); i++) {
                Allure.step("Processing article at index: " + i);

                String newsType = WebActions.safeFindText(webElementList.get(i), newsPage.getNewsTypeBy());
                Allure.step("Detected news type: " + newsType);

                // Skip if the date contains "X days ago" (case-insensitive)
                String newsDate = WebActions.safeFindText(webElementList.get(i), newsPage.getTimeStampBy());
                if (newsDate.toLowerCase().matches("\\d+\\s+days?\\s+ago")) {
                    Allure.step("Article skipped because its date is relative ('X days ago').");
                    continue;
                }

                // Fill list only with WHO Joint News Release / Statement or any article for other sites
                if (!newsPage.equals(whoNewsPage) ||
                        newsType.equals("Joint News Release") ||
                        newsType.equals("Statement")) {

                    Allure.step("Article meets filter criteria. Extracting details...");

                    NewsObject newsObject = new NewsObject(
                            webDriver.getCurrentUrl(),
                            WebActions.safeFindText(webElementList.get(i), newsPage.getTimeStampBy()),
                            WebActions.safeFindText(webElementList.get(i), newsPage.getNewsTypeBy()),
                            WebActions.safeFindText(webElementList.get(i), newsPage.getNewsTitleBy()),
                            WebActions.safeFindAttribute(webElementList.get(i), newsPage.getNewsUrlBy(), "href"),
                            DateUtils.convertRelativeToAbsolute(
                                    WebActions.safeFindText(webElementList.get(i), newsPage.getTimeStampBy()),
                                    "dd/MMMM/yyyy",
                                    zone
                            )
                    );

                    newsObjectsList.add(newsObject);
                    Allure.step("Added article: \"" + newsObject.getTitle() + "\"");
                } else {
                    Allure.step("Article skipped due to type filtering.");
                }
            }

            Allure.step("FINISH Method storeNewsInfo - Total stored articles: " + newsObjectsList.size());

        } catch (Exception e) {
            Allure.step("Error occurred in storeNewsInfo: " + e.getMessage());
            throw e; // Rethrow so the test still fails
        }

        return newsObjectsList;
    }
}
