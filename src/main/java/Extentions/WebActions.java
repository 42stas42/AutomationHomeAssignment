package Extentions;

import Utilities.CommonOptions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.NoSuchElementException;

public class WebActions extends CommonOptions {

    public static void click(WebElement webElement) throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
        } catch (Exception e) {
            throw new Exception("Can't click element" + e.getMessage());
        }
    }

    public static void sendText(WebElement webElement, String text) throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement)).sendKeys(text);
        } catch (Exception e) {
            throw new Exception("Can't click element" + e.getMessage());
        }
    }

    public static void selectElementFromDropDown(WebElement webElement, String text) throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
        } catch (Exception e) {
            throw new Exception("Can't select element" + e.getMessage());
        }
    }

    public static void centerWebElementWithJS(WebElement webElement) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", webElement);
    }

    public static String safeFindText(WebElement parent, By locator) {
        try {
            if (!parent.isDisplayed()) {
                centerWebElementWithJS(parent);
                wait.until(ExpectedConditions.visibilityOf(parent));
            }
            WebElement element = parent.findElement(locator);
            String text = element.getText();
            return text != null ? text.trim() : "";
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    @Step("run method safeFindAttribute")
    public static String safeFindAttribute(WebElement parent, By locator, String attribute) {
        try {
            WebElement element = parent.findElement(locator);
            String value = element.getAttribute(attribute);
            return value != null ? value.trim() : "";
        } catch (Exception e) {
            return "The Data Returned Empty From The Web";
        }
    }

}
