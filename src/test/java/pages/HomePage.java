package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.logs.Log;
import utils.reader.ConfigReader;
import utils.WebDriver.Driver;

public class HomePage extends BasePage{

    public HomePage(){
    }

    @FindBy(xpath = "//span[.='More']/..")
    private WebElement moreDropdown;

    @FindBy(xpath = "//a/h5[.='Careers']")
    private WebElement careersNavigationLink;

    public HomePage navigateToInsiderPage(){
        Log.info("Opening Insider Home Page");
        Driver.get().get(ConfigReader.getInstance().get("url"));
        acceptCookies();
        return this;
    }

    public HomePage verifyUrl(String url){
        Log.info("Checking Insider home page is opened or not");
        Assert.assertEquals(Driver.get().getCurrentUrl(), url);
        return this;
    }

    public CareersPage navigateCareerPage(){
        Log.info("Selecting \"More\" menu in navigation bar then clicking \"Careers\" page link");
        click(moreDropdown);
        click(careersNavigationLink);
        return new CareersPage();
    }
}
