package Verifications;

import InfraObjects.NewsObject;
import Utilities.CommonOptions;
import Utilities.ConfigReader;
import Utilities.DateUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Locale;

public class Verifications extends CommonOptions {


    @Step("verifyNewsData")
    public static void verifyNewsData(List<NewsObject> newsObjectsList) {

        Allure.step("Starting verification of " + newsObjectsList.size() + " news articles.");

        for (NewsObject news : newsObjectsList) {
            Allure.step("Verifying article: \"" + news.getTitle() + "\" from " + news.getOrigin());

            // Title check
            Allure.step("Checking that the article title is not empty.");
            softAssert.assertTrue(news.getTitle() != null && !news.getTitle().isEmpty(),
                    "FAILURE: The article title is empty");

            // Type-specific checks
            if (!news.getType().equals("QUICK READ")) {
                Allure.step("Verifying article type is not QUICK READ - performing URL and year checks.");

                Allure.step("Checking that URL starts with 'https:'.");
                softAssert.assertTrue(news.getNewsUrl().startsWith("https:"),
                        "FAILURE: The URL doesn't start with 'https:'");

                String extractedYear = DateUtils.safeExtractYear(news.getDate());
                Allure.step("Extracted year from date: " + extractedYear);
                softAssert.assertTrue(news.getNewsUrl().contains(extractedYear),
                        "FAILURE: The article URL doesn't contain the year of the published date");
            }

            // Date format verification per site origin
            if (news.getOrigin().contains("who")) {
                Allure.step("Verifying WHO site date format: " + ConfigReader.getProperty("WHO_DATE_FORMAT"));
                softAssert.assertTrue(
                        DateUtils.isValidDate(news.getDate(), ConfigReader.getProperty("WHO_DATE_FORMAT"), Locale.ENGLISH),
                        "FAILURE: The date pattern of the \"" +
                                news.getTitle().substring(0, 30).split(",")[0] +
                                "...\" article not as expected for the WHO site. " +
                                "Expected: " + ConfigReader.getProperty("WHO_DATE_FORMAT") +
                                " ACTUAL: " + news.getDate()
                );
            } else if (news.getOrigin().contains("apple")) {
                Allure.step("Verifying APPLE site date format: " + ConfigReader.getProperty("APPLE_DATE_FORMAT"));
                softAssert.assertTrue(
                        DateUtils.isValidDate(news.getDate(), ConfigReader.getProperty("APPLE_DATE_FORMAT"), Locale.ENGLISH),
                        "FAILURE: The date pattern of the \"" +
                                news.getTitle().substring(0, 30).split(",")[0] +
                                "...\" article not as expected for the APPLE site. " +
                                "Expected: " + ConfigReader.getProperty("APPLE_DATE_FORMAT") +
                                " ACTUAL: " + news.getDate()
                );
            }
        }

        Allure.step("Finished verifying all news articles.");
    }

}
