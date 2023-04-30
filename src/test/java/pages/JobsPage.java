package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.logs.Log;

import java.util.List;

public class JobsPage extends BasePage {
    @FindBy(css = "#filter-by-department")
    private WebElement departmentFilterDropdown;

    @FindBy(css = "#filter-by-location")
    private WebElement locationFilterDropdown;

    @FindBy(css = "#jobs-list>div")
    private List<WebElement> jobList;

    @FindBy(xpath = "//div[@id='jobs-list']//p")
    private WebElement firstJobPosition;

    public String getSelectedDepartment() {
        return new Select(departmentFilterDropdown).getFirstSelectedOption().getText();
    }
    public JobsPage verifySelectedDepartment(String department){
        Log.info("Verifying filtered department match with the specified department: " + department);
        Assert.assertEquals(getSelectedDepartment(), department);
        return this;
    }

    public JobsPage filterByLocation(Location location) {
        Log.info("Filtering job location as: \"" + getLocationText(location) + "\"");
        select = new Select(locationFilterDropdown);
        select.selectByVisibleText(getLocationText(location));
        waitUntilLocationChange(select, location);
        return this;
    }

    public void waitUntilLocationChange(Select select, Location location){
        ExpectedCondition<Boolean> expectation = expect ->
                select.getFirstSelectedOption().getText().equals(getLocationText(location));
        wait.until(expectation);
        waitFor();
    }

    // Not used all locations. Used some of them for creating a template
    public enum Location {
        ALL, ISTANBUL_TURKEY, ANKARA_TURKEY,
        TURKEY, SYDNEY_AUSTRALIA, PARIS_FRANCE
    }

    public static String getLocationText(Location location) {
        return switch (location) {
            case ALL -> "All";
            case ISTANBUL_TURKEY -> "Istanbul, Turkey";
            case ANKARA_TURKEY -> "Ankara, Turkey";
            case TURKEY -> "Turkey";
            case SYDNEY_AUSTRALIA -> "Sydney, Australia";
            case PARIS_FRANCE -> "Paris, France";
        };
    }

    public void verifyJobProperties(String position, String department, String location){
        scrollUntilVisible(firstJobPosition);
        waitFor();
        for (WebElement parent : jobList) {
            WebElement positionElement = parent.findElement(By.xpath(".//p"));
            WebElement departmentElement = parent.findElement(By.xpath(".//span"));
            WebElement locationElement = parent.findElement(By.xpath(".//div[contains(@class,'position-location')]"));
            WebElement applyNowButton = parent.findElement(By.xpath(".//a"));

            Log.info("Verifying job position match with specified position: " + position);
            Assert.assertTrue(positionElement.getText().contains(position));

            Log.info("Verifying job department match with specified department: " + department);
            Assert.assertEquals(departmentElement.getText(),department);

            Log.info("Verifying job location match with specified location: " + location);
            Assert.assertEquals(locationElement.getText(),location);

            Log.info("Verifying that apply button is displayed");
            scrollToElement(applyNowButton);
            actions.moveToElement(applyNowButton).build().perform();
            Assert.assertTrue(applyNowButton.isEnabled());

            //TODO: Click apply now button and check url contains 'lever'
        }
    }

}
