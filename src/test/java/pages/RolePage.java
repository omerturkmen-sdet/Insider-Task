package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.logs.Log;

public class RolePage extends BasePage{

    @FindBy(xpath = "//a[contains(text(),'See all')][contains(text(),'jobs')]")
    private WebElement seeAllJobsButton;

    public JobsPage seeAllJobs(){
        Log.info("Displaying all jobs for the role");
        click(seeAllJobsButton);
        return new JobsPage();
    }

}
