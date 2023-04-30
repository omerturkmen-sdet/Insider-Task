package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.Driver;

import java.util.List;

public class CareersPage extends BasePage {

    public CareersPage(){
        Assert.assertEquals(Driver.get().getCurrentUrl(), "https://useinsider.com/careers/");
    }

    @FindBy(xpath = "//h3[@class='category-title-media']")
    private WebElement teamsBlock;

    @FindBy(xpath = "//h3[@class='category-title-media ml-0']")
    private WebElement locationsBlock;

    @FindBy(xpath = "//h2[.='Life at Insider']")
    public WebElement lifeAtInsiderBlock;

    @FindBy(xpath = "//a[.='See all teams']")
    public WebElement seeAllTeamsButton;

    // We can also locate this with only "//a/h3", but this is more dynamic way.
    // If some non-related element added with "h3" tag in the page, this locator also locates that
    @FindBy(xpath = "//div[contains(@class,'career-load-more')]//h3")
    private List<WebElement> rolesList;

    public CareersPage verifyBlocksAreDisplayed(){
        verifyElementIsDisplayed(teamsBlock);
        verifyElementIsDisplayed(locationsBlock);
        verifyElementIsDisplayed(lifeAtInsiderBlock);
        return this;
    }

    /**
     * Exact match required to find expected role.
     * Using enum prevents misspelling
     * @param role This is the enum object for selecting role
     */
    public RolePage navigateRole(Role role) {
        expandJobTitle();
        for (WebElement element : rolesList) {
            if (!element.isDisplayed()){
                scrollUntilVisible(element);
            }
            if (element.getText().equals(getRole(role))) {
                click(element);
                break;
            }
        }
        return new RolePage();
    }

    /**
     * Because of the network speed it might load slower.
     * With checking rolesList.size() allows us to be sure all roles are listed on page
     */
    private void expandJobTitle(){
        int initialSize = rolesList.size();
        click(seeAllTeamsButton);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//a/h3"),initialSize));
    }
    public static String getRole(Role role) {
        return switch (role) {
            case CUSTOMER_SUCCESS -> "Customer Success";
            case SALES -> "Sales";
            case PRODUCT_ENGINEERING -> "Product & Engineering";
            case FINANCE_AND_BUSINESS_SUPPORT -> "Finance & Business Support";
            case MARKETING -> "Marketing";
            case CEOS_EXECUTIVE_OFFICE -> "CEO’s Executive Office";
            case PURCHASING_OPERATIONS -> "Purchasing & Operations";
            case PEOPLE_AND_CULTURE -> "People and Culture";
            case BUSINESS_INTELLIGENCE -> "Business Intelligence";
            case SECURITY_ENGINEERING -> "Security Engineering";
            case PARTNERSHIP -> "Partnership";
            case QUALITY_ASSURANCE -> "Quality Assurance";
            case MOBILE_BUSINESS_UNIT -> "Mobile Business Unit";
            case PARTNER_SUPPORT_DEVELOPMENT -> "Partner Support Development";
            case PRODUCT_DESIGN -> "Product Design";
        };
    }

    public enum Role {
        CUSTOMER_SUCCESS, SALES, PRODUCT_ENGINEERING, FINANCE_AND_BUSINESS_SUPPORT, MARKETING,
        CEOS_EXECUTIVE_OFFICE, PURCHASING_OPERATIONS, PEOPLE_AND_CULTURE, BUSINESS_INTELLIGENCE,
        SECURITY_ENGINEERING, PARTNERSHIP, QUALITY_ASSURANCE, MOBILE_BUSINESS_UNIT,
        PARTNER_SUPPORT_DEVELOPMENT, PRODUCT_DESIGN
    }


}
