package PageObjects;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CovidSummaryPage {

    @FindBy(xpath = "//*[@id='PageContent_C676_Col00']//table//tbody")
    private List<WebElement> table11Rows;

    @FindBy(xpath = "//*[@id='PageContent_C570_Col00']//table//tbody/tr")
    private List<WebElement> table13Rows;


    public List<WebElement> getTable11Rows() {
        return table11Rows;
    }

    public List<WebElement> getTable13Rows() {
        return table13Rows;
    }

    @Step("RUN method positivityFrom11")
    public Map<String, String> positivityFrom11(List<WebElement> table11Rows) {
        Map<String, String> map = new HashMap<>();

        try {
            Allure.step("Starting to parse table with " + table11Rows.size() + " rows");

            for (int i = 0; i < table11Rows.size(); i++) {
                Allure.step("Processing row #" + (i + 1));

                List<WebElement> tds = table11Rows.get(i).findElements(By.xpath(".//td"));
                if (tds.size() >= 3) {
                    String region = table11Rows.get(i)
                            .findElement(By.xpath("./tr/th"))
                            .getText()
                            .trim();
                    Allure.step("Region found: " + region);

                    String positivity = tds.get(3).getText().trim();
                    Allure.step("Positivity value: " + positivity);

                    map.put(region, positivity);
                } else {
                    Allure.step("Row #" + (i + 1) + " skipped - less than 3 columns found");
                }
            }

            Allure.step("Finished parsing table");

        } catch (Exception e) {
            Allure.step("Error occurred in positivityFrom11: " + e.getMessage());
            throw e; // rethrow so test can fail
        }

        return map;
    }

    @Step("RUN method positivityFrom13")
    public Map<String, String> positivityFrom13(List<WebElement> table13Rows) {
        Map<String, String> map = new HashMap<>();

        try {
            Allure.step("Starting to parse table with " + table13Rows.size() + " rows");

            for (int i = 0; i < table13Rows.size(); i++) {
                Allure.step("Processing row #" + (i + 1));

                List<WebElement> tds = table13Rows.get(i).findElements(By.xpath(".//td"));
                if (tds.size() >= 3) {
                    String region = table13Rows.get(i)
                            .findElement(By.xpath("./th"))
                            .getText()
                            .trim();
                    Allure.step("Region found: " + region);

                    String positivity = tds.get(2).getText().trim();
                    Allure.step("Positivity value: " + positivity);

                    map.put(region, positivity);
                } else {
                    Allure.step("Row #" + (i + 1) + " skipped - less than 3 columns found");
                }
            }

            Allure.step("Finished parsing table");

        } catch (Exception e) {
            Allure.step("Error occurred in positivityFrom13: " + e.getMessage());
            throw e; // rethrow to ensure the test fails
        }

        return map;
    }
}
