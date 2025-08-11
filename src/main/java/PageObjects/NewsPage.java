package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface NewsPage {

    List<WebElement> getListItem();

    By getNewsTypeBy();

    By getTimeStampBy();

    By getNewsTitleBy();

    By getNewsUrlBy();
}

