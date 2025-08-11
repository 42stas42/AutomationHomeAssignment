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
        Allure.step("START Method storeNewsInfo");
        NewsObject newsObject;
        List<NewsObject> newsObjectsList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            //Fill up the 'newsObjectsList' only with articles of type “Joint News Release” and “Statement” for the WHO site
            if (!newsPage.equals(whoNewsPage) || webElementList.get(i).findElement(newsPage.getNewsTypeBy()).getText().equals("Joint News Release")
                    || webElementList.get(i).findElement(newsPage.getNewsTypeBy()).getText().equals("Statement")) {
                newsObject = new NewsObject(
                        webDriver.getCurrentUrl(),
                        WebActions.safeFindText(webElementList.get(i), newsPage.getTimeStampBy()),
                        WebActions.safeFindText(webElementList.get(i), (newsPage.getNewsTypeBy())),
                        WebActions.safeFindText(webElementList.get(i), (newsPage.getNewsTitleBy())),
                        WebActions.safeFindAttribute(webElementList.get(i), newsPage.getNewsUrlBy(), "href"),
                        DateUtils.convertRelativeToAbsolute(WebActions.safeFindText(webElementList.get(i), newsPage.getTimeStampBy()), "dd/MMMM/yyyy", zone)
                );
                newsObjectsList.add(newsObject);
            }

        }

        Allure.step("FINISH Method storeNewsInfo");
        return newsObjectsList;
    }
}
