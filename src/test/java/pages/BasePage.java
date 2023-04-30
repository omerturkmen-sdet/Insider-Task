package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;
import utilities.WebUtils;

import java.time.Duration;

public abstract class BasePage {

    /**
     * We can use POM with defining our WebDriver object by using PageFactory class
     */
    public BasePage(){
        PageFactory.initElements(Driver.get(),this);
    }

    public WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
    JavascriptExecutor js = (JavascriptExecutor) Driver.get();
    Actions actions = new Actions(Driver.get());

    @FindBy(xpath = "//a[@id='wt-cli-accept-btn']")
    private WebElement acceptNecessaryCookiesButton;

    @FindBy(xpath = "//a[@id='wt-cli-accept-all-btn']")
    private WebElement acceptAllCookiesButton;

    public void acceptCookies(){
        try {
            acceptNecessaryCookiesButton.click();
        }catch (Exception e){
        }
    }


    public void scrollUntilVisible(WebElement element){
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        js.executeScript(scrollElementIntoMiddle, waitUntilVisible(element));
        waitFor();
    }

    public void scrollToElement(WebElement element){
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) Driver.get()).executeScript(scrollElementIntoMiddle, element);
        waitFor();
    }

    public WebElement waitUntilVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(WebElement element){
        scrollToElement(element);
        clickWithJS(element);
    }

    public void clickWithJS(WebElement element){
        js.executeScript("arguments[0].click();", waitUntilClickable(element));
    }

    public void waitFor(){
        try {
            Thread.sleep(500);
        }catch (Exception e){}
    }

    public WebElement waitUntilClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
