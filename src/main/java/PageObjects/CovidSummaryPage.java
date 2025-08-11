package PageObjects;

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
        for (int i = 0; i < table11Rows.size(); i++) {
            List<WebElement> tds = table11Rows.get(i).findElements(By.xpath(".//td"));
            if (tds.size() >= 3) {
                String region = table11Rows.get(i).findElement(By.xpath("./tr/th")).getText().trim();
                String positivity = "";
                positivity = tds.get(3).getText().trim();
                map.put(region, positivity);
            }
        }
        return map;
    }

    @Step("RUN method positivityFrom13")
    public Map<String, String> positivityFrom13(List<WebElement> table13Rows) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < table13Rows.size(); i++) {
            List<WebElement> tds = table13Rows.get(i).findElements(By.xpath(".//td"));
            if (tds.size() >= 3) {
                String region = table13Rows.get(i).findElement(By.xpath("./th")).getText().trim();
                String positivity = "";
                positivity = tds.get(2).getText().trim();
                map.put(region, positivity);
            }
        }
        return map;
    }
}
