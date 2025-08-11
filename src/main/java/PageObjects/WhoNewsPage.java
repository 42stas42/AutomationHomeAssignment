package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WhoNewsPage implements NewsPage {

    @FindBy(xpath = "//*[@class='list-view--item vertical-list-item']")
    List<WebElement> listItem;

    By timeStampBy = By.xpath(".//*[@class='timestamp']");
    By newsTypeBy = By.xpath(".//*[@class='sf-tags-list-item']");
    By newsTitleBy = By.xpath(".//*[@class='heading text-underline']");
    By newsUrlBy = By.xpath(".//*[@href]");

    public List<WebElement> getListItem() {
        return listItem;
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


}
