package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RolePage extends BasePage{

    @FindBy(xpath = "//a[contains(text(),'See all')][contains(text(),'jobs')]")
    private WebElement seeAllJobsButton;

    public JobsPage seeAllJobs(){
        click(seeAllJobsButton);
        return new JobsPage();
    }

}
