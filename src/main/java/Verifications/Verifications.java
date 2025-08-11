package Verifications;

import InfraObjects.NewsObject;
import Utilities.CommonOptions;
import Utilities.ConfigReader;
import Utilities.DateUtils;

import java.util.List;
import java.util.Locale;

public class Verifications extends CommonOptions {

    public static void verifyNewsData(List<NewsObject> newsObjectsList) {

        for (NewsObject news : newsObjectsList) {
            softAssert.assertTrue(!news.getTitle().isEmpty() && news.getTitle() != null, "FAILURE: The article title is empty");
            if (!news.getType().equals("QUICK READ")) {
                softAssert.assertTrue(news.getNewsUrl().startsWith("https:"), "FAILURE: The URL doesn't start with 'https:'");
                softAssert.assertTrue(news.getNewsUrl().contains(DateUtils.safeExtractYear(news.getDate())), "FAILURE: The article doesn't contain the year of the published date");
            }

            //Verify date format per site pattern
            if (news.getOrigin().contains("who")) {
                softAssert.assertTrue(DateUtils.isValidDate(news.getDate(), ConfigReader.getProperty("WHO_DATE_FORMAT"), Locale.ENGLISH),
                        "FAILURE: The date pattern  of the " + news.getTitle().substring(0, 30).split(",")[0] + "... article not as expected for the WHO site" + "Expected: " + ConfigReader.getProperty("WHO_DATE_FORMAT") + "ACTUAL: " + DateUtils.isValidDate(news.getDate(), ConfigReader.getProperty("WHO_DATE_FORMAT"), Locale.ENGLISH));
            } else if (news.getOrigin().contains("apple"))
                softAssert.assertTrue(DateUtils.isValidDate(news.getDate(), ConfigReader.getProperty("APPLE_DATE_FORMAT"), Locale.ENGLISH),
                        "FAILURE: The date pattern  of the " + news.getTitle().substring(0, 30).split(",")[0] + "... article not as expected for the APPLE site" + "Expected: " + ConfigReader.getProperty("WHO_DATE_FORMAT") + "ACTUAL: " + DateUtils.isValidDate(news.getDate(), ConfigReader.getProperty("APPLE_DATE_FORMAT"), Locale.ENGLISH));
        }

    }

}
