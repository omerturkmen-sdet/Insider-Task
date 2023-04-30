package tests;

import org.testng.annotations.Test;
import pages.CareersPage;
import pages.JobsPage;

public class Task extends Hooks {


    @Test(description = "Testing something")
    public void task() {
        page.navigateToInsiderPage()
                .verifyUrl("https://useinsider.com/")
                .navigateCareerPage()
                .verifyBlocksAreDisplayed()
                .navigateRole(CareersPage.Role.QUALITY_ASSURANCE)
                .seeAllJobs()
                .filterByLocation(JobsPage.Location.ISTANBUL_TURKEY)
                .verifySelectedDepartment("Quality Assurance")
                .verifyJobProperties("Quality Assurance","Quality Assurance", "Istanbul, Turkey");
    }
}
