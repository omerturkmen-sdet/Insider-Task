package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.WebUtils;

import java.util.List;

public class HomePage extends BasePage{

    public HomePage(){
    }

    @FindBy(xpath = "//span[.='More']/..")
    private WebElement moreDropdown;

    @FindBy(xpath = "//a/h5[.='Careers']")
    private WebElement careersNavigationLink;

    public HomePage navigateToInsiderPage(){
        Driver.get().get(ConfigReader.getInstance().get("url"));
        acceptCookies();
        return this;
    }

    public HomePage verifyUrl(String url){
        Assert.assertEquals(Driver.get().getCurrentUrl(), url);
        return this;
    }

    public CareersPage navigateCareerPage(){
        WebUtils.click(moreDropdown);
        WebUtils.click(careersNavigationLink);
        return new CareersPage();
    }
}
