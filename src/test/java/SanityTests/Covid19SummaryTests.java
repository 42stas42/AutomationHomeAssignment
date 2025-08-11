package SanityTests;

import Utilities.CommonOptions;
import Utilities.ConfigReader;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class Covid19SummaryTests extends CommonOptions {

    @Test
    @Description("Verifies the test positivity rate data for each region, between table 1.1 and table 1.3")
    public void covid19SummaryTest() {

        //Go to https://data.who.int/dashboards/covid19/summary
        Allure.step("Go to https://data.who.int/dashboards/covid19/summary");
        webDriver.get(ConfigReader.getProperty("URL_3"));

        Allure.step("Verify: Dates between table 1.1 and 1.3");
        Assert.assertEquals(covidSummaryPage.getTable11Date().getText(), covidSummaryPage.getTable13Date().getText(), "FAILURE: Dates between table 1.1 and 1.3 differ");

        //Load hash maps wit data from 1.1 and 1.3 tables accordingly
        Allure.step("Load hash maps wit data from 1.1 and 1.3 tables accordingly");
        Map<String, String> m11 = covidSummaryPage.positivityFrom11(covidSummaryPage.getTable11Rows());
        Map<String, String> m13 = covidSummaryPage.positivityFrom13(covidSummaryPage.getTable13Rows());

        //Verify Region name and its positivity data
        Allure.step("Verify Region name and its positivity data");
        for (String region : m11.keySet()) {
            Assert.assertTrue(m13.containsKey(region), "FAILURE: Region missing in 1.3: " + region);
            Assert.assertEquals(m13.get(region), m11.get(region), "FAILURE Mismatch in positivity data for region " + region);
        }

    }

}
