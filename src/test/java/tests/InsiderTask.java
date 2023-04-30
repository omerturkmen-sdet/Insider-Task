package tests;

import org.testng.annotations.Test;
import pages.CareersPage;
import pages.JobsPage;

import java.lang.reflect.Method;

import static utils.extentreports.ExtentTestManager.startTest;

public class InsiderTask extends BaseTest {


    @Test(description = "Quality Assurance Jobs Validation")
    public void qualityAssurance_JobAdvertsValidation(Method method) {

        startTest(method.getName(), "Quality Assurance Jobs Validation");
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
