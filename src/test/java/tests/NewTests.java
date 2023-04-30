package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.JobsPage;
import pages.RolePage;
import utilities.Driver;
import utilities.WebUtils;

import java.time.Duration;
import java.util.List;

public class NewTests {



            /*
        1. Visit https://useinsider.com/ and check Insider home page is opened or not
        2. Select “More” menu in navigation bar, select “Careers” and check Career page, its
        Locations, Teams and Life at Insider blocks are opened or not
        3. Click “See All Teams”, select Quality Assurance, click “See all QA jobs”, filter jobs by
        Location - Istanbul, Turkey and department - Quality Assurance, check presence of
        jobs list
        4. Check that all jobs’ Position contains “Quality Assurance”, Department contains
        “Quality Assurance”, Location contains “Istanbul, Turkey” and “Apply Now” button
        5. Click “Apply Now” button and check that this action redirects us to Lever Application
        form page
        - Test case should be written using any programming language and framework
        (Python or Java + Selenium would be preferable)
        - No BDD(Cucumber, Quantum, Codeception, etc.) frameworks are allowed
        - Test case should fully meet POM requirements
        - Buttons, dropdowns and other elements should have optimized Xpath and
        CSS selectors
        - Assertions should be used in test case
        - Code should be clean and readable
         */

    JavascriptExecutor js;

    @Test
    public void test() throws InterruptedException {


        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        js = (JavascriptExecutor) Driver.get();

        Driver.get().get("https://useinsider.com/");
        Driver.get().findElement(By.xpath("//a[@id='wt-cli-accept-btn']")).click();
        Assert.assertEquals(Driver.get().getCurrentUrl(), "https://useinsider.com/");

        Driver.get().findElement(By.xpath("//span[.='More']/..")).click();
        Driver.get().findElement(By.xpath("//a/h5[.='Careers']")).click();


        waitForPageToLoad();
        Assert.assertEquals(Driver.get().getCurrentUrl(), "https://useinsider.com/careers/");

        Thread.sleep(1000);

        validateBlocksAreDisplayed();
        Thread.sleep(1000);

        //See all teams
        WebElement seeAllTeamsBtn = Driver.get().findElement(By.xpath("//a[.='See all teams']"));
        scrollToElement(seeAllTeamsBtn);
        clickWithJS(seeAllTeamsBtn);

        WebElement qualityAssurance = Driver.get().findElement(By.xpath("//h3[.='Quality Assurance']"));
        scrollToElement(qualityAssurance);
        clickWithJS(qualityAssurance);

        //See all jobs
        RolePage rolePage = new RolePage();
        rolePage.seeAllJobs();

        //filter jobs by Location - Istanbul, Turkey and department - Quality Assurance, check presence of


        JobsPage page = new JobsPage();

        page.filterByLocation(JobsPage.Location.ISTANBUL_TURKEY);

        waitForPageToLoad();

        Thread.sleep(1500);
        WebElement jobList = Driver.get().findElement(By.xpath("//div[@id='jobs-list']/div"));
        Thread.sleep(1500);
        Actions actions = new Actions(Driver.get());

        WebElement position = Driver.get().findElement(By.xpath("//div[@id='jobs-list']/div//p"));
        String positionText = position.getText();
        String departmentText = Driver.get().findElement(By.xpath("//div[@id='jobs-list']/div//span")).getText();
        String locationText = jobList.findElement(By.xpath(".//div[contains(@class,'position-location')]")).getText();
        WebElement applyNowButton = Driver.get().findElement(By.xpath("//div[@id='jobs-list']/div//a"));

        System.out.println("positionText = " + positionText);
        System.out.println("departmentText = " + departmentText);
        System.out.println("locationText = " + locationText);
        Assert.assertTrue(positionText.contains("Quality Assurance"));
        Assert.assertEquals(departmentText, "Quality Assurance");
        Assert.assertEquals(locationText, "Istanbul, Turkey");
        scrollToElement(applyNowButton);
        Thread.sleep(1000);
        actions.moveToElement(applyNowButton).build().perform();
        new WebDriverWait(Driver.get(),Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(applyNowButton));
        Thread.sleep(1000);
        Assert.assertTrue(applyNowButton.isDisplayed());


        Driver.closeDriver();
    }

    private void moveToElement(WebElement element) {
        String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";


        ((JavascriptExecutor) Driver.get()).executeScript(javaScript, element);
    }

    private void scrollToElement(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        js.executeScript(scrollElementIntoMiddle, element);

    }

    public void clickWithJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);
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

    private void validateBlocksAreDisplayed() throws InterruptedException {
        WebElement teamsBlock = Driver.get().findElement(By.xpath("//h3[@class='category-title-media']"));
        WebElement locationsBlock = Driver.get().findElement(By.xpath("//h3[@class='category-title-media ml-0']"));
        WebElement lifeAtInsiderBlock = Driver.get().findElement(By.xpath("//h2[.='Life at Insider']"));

        scrollToElement(teamsBlock);
        Assert.assertTrue(teamsBlock.isDisplayed());

        Thread.sleep(1500);

        scrollToElement(locationsBlock);
        Assert.assertTrue(lifeAtInsiderBlock.isDisplayed());
        Thread.sleep(1500);

        scrollToElement(lifeAtInsiderBlock);
        Assert.assertTrue(lifeAtInsiderBlock.isDisplayed());
        Thread.sleep(1500);
    }
}
