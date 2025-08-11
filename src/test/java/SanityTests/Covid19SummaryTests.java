package SanityTests;

import Utilities.CommonOptions;
import Utilities.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class Covid19SummaryTests extends CommonOptions {

    @Test
    public void covid19SummaryTest() {

        //Go to https://data.who.int/dashboards/covid19/summary
        webDriver.get(ConfigReader.getProperty("URL_3"));

        //Load hash maps wit data from 1.1 and 1.3 tables accordingly
        Map<String, String> m11 = covidSummaryPage.positivityFrom11(covidSummaryPage.getTable11Rows());
        Map<String, String> m13 = covidSummaryPage.positivityFrom13(covidSummaryPage.getTable13Rows());

        //Verify Region name and its positivity data
        for (String region : m11.keySet()) {
            Assert.assertTrue(m13.containsKey(region), "FAILURE: Region missing in 1.3: " + region);
            Assert.assertEquals(m13.get(region), m11.get(region), "FAILURE Mismatch in positivity data for region " + region);
        }

    }

}
