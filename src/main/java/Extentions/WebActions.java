package Extentions;

import Utilities.CommonOptions;
import io.qameta.allure.Allure;
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
        try {
            Allure.step("Centering WebElement on the screen using JavaScript.");
            Allure.step("Target element: " + (webElement != null ? webElement.toString() : "null"));

            if (webElement == null) {
                Allure.step("WARNING: WebElement is null. Skipping scroll action.");
                return;
            }

            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", webElement);
            Allure.step("Scroll action completed successfully.");

        } catch (Exception e) {
            Allure.step("Error while centering WebElement: " + e.getMessage());
            throw e; // Rethrow so the test fails if needed
        }
    }

    @Step("run method safeFindText")
    public static String safeFindText(WebElement parent, By locator) {
        try {
            Allure.step("Attempting to safely find text using locator: " + locator.toString());
            Allure.step("Parent element: " + (parent != null ? parent.toString() : "null"));

            if (parent == null) {
                Allure.step("WARNING: Parent WebElement is null. Returning empty string.");
                return "";
            }

            if (!parent.isDisplayed()) {
                Allure.step("Parent element is not displayed. Centering on screen and waiting for visibility.");
                centerWebElementWithJS(parent);
                wait.until(ExpectedConditions.visibilityOf(parent));
            }

            WebElement element = parent.findElement(locator);
            Allure.step("Element found. Retrieving text...");

            String text = element.getText();
            String result = text != null ? text.trim() : "";

            Allure.step("Extracted text: \"" + result + "\"");
            return result;

        } catch (NoSuchElementException e) {
            Allure.step("Element not found for locator: " + locator.toString() + ". Returning empty string.");
            return "";
        } catch (Exception e) {
            Allure.step("Unexpected error in safeFindText: " + e.getMessage());
            return "";
        }
    }


    @Step("run method safeFindAttribute")
    public static String safeFindAttribute(WebElement parent, By locator, String attribute) {
        try {
            Allure.step("Attempting to find attribute \"" + attribute + "\" using locator: " + locator.toString());
            Allure.step("Parent element: " + (parent != null ? parent.toString() : "null"));

            if (parent == null) {
                Allure.step("WARNING: Parent WebElement is null. Returning default message.");
                return "The Data Returned Empty From The Web";
            }

            WebElement element = parent.findElement(locator);
            Allure.step("Element found. Retrieving attribute: " + attribute);

            String value = element.getAttribute(attribute);
            String result = value != null ? value.trim() : "";

            Allure.step("Extracted attribute value: \"" + result + "\"");
            return result;

        } catch (Exception e) {
            Allure.step("Error while retrieving attribute \"" + attribute + "\": " + e.getMessage());
            return "The Data Returned Empty From The Web";
        }
    }

}
