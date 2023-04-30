package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebUtils {

    public static void click(WebElement element) {
        scrollToMiddle(element);
        clickWithJS(element);
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.get()).executeScript ("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
    }

    public static void scrollToElement1(WebElement element){
        ((JavascriptExecutor)Driver.get()).executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollToMiddle(WebElement element){
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) Driver.get()).executeScript(scrollElementIntoMiddle, element);
    }

    public static void clickWithJS(WebElement element){
        ((JavascriptExecutor)Driver.get()).executeScript("arguments[0].click();",
                waitUntilClickable(element));
    }

    public static WebElement waitUntilClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForPageToLoad() {
        ExpectedCondition<Boolean> expectation =
                webDriver -> {
                    assert webDriver != null;
                    return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
                };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void waitUntilCondition(Boolean condition){
        ExpectedCondition<Boolean> expectation = expect -> condition;
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void moveToElement(WebElement element){
        Actions actions = new Actions(Driver.get());
        actions.moveToElement(element).build().perform();
    }

    public static void waitUntilVisible(WebElement element){
        new WebDriverWait(Driver.get(),Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitFor(){
        try {
            Thread.sleep(500);
        }catch (Exception e){}
    }


    public static void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(15));
        wait.until(webDriver -> "complete".equals(((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")));
    }
}
