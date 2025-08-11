package DataProviders;

import Utilities.CommonOptions;
import Utilities.ConfigReader;
import org.testng.annotations.DataProvider;

public class NewsDataProvider extends CommonOptions{

    @DataProvider(name = "newsDataProvider")
    public Object[][] newsDataProvider() throws InterruptedException {
        return new Object[][]{

                {ConfigReader.getProperty("URL_1"), whoNewsPage},
                {ConfigReader.getProperty("URL_2"), appleNewsRoomPage},
        };
    }

}
