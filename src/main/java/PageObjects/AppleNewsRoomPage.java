package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AppleNewsRoomPage implements NewsPage {

    @FindBy(xpath = "//*[@role='listitem'][contains(@class, 'tile-item')]")
    List<WebElement> ListItem;

    By ListItemBy = By.xpath("//*[@role='listitem'][contains(@class, 'tile-item')]");

    By timeStampBy = By.xpath(".//*[@data-ts]");
    By newsTypeBy = By.xpath(".//*[@class='tile__head']//*[contains(@class, 'category')]");
    By newsTitleBy = By.xpath(".//*[@class='tile__headline']");
    By newsUrlBy = By.xpath(".//*[@href]");///

    public List<WebElement> getListItem() {
        return ListItem;
    }

    public By getTimeStampBy() {
        return timeStampBy;
    }

    public By getNewsTypeBy() {
        return newsTypeBy;
    }

    public By getNewsTitleBy() {
        return newsTitleBy;
    }

    public By getNewsUrlBy() {
        return newsUrlBy;
    }

    public By getListItemBy() {
        return ListItemBy;
    }
}
