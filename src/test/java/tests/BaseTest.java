package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.WebDriver.Driver;
import utils.logs.Log;
import utils.mailSender.GmailSender;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    JavascriptExecutor js;
    HomePage page;
    List<String> executedTests = new ArrayList<>();

    @BeforeClass
    public void classLevelSetup() {
        Log.info("Tests is starting!");
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @BeforeMethod
    public void methodLevelSetup() {
        page = new HomePage();
        js = (JavascriptExecutor) Driver.get();
    }

    @AfterClass
    public void teardown() {
        Log.info("Tests are ending!");
        Driver.closeDriver();
    }

    @AfterSuite
    public void suiteTearDown() throws Exception {

        new GmailSender().sendMail("QA Automation Report",
                getExecutedTests(),
                "extent-reports/extent-report.html",
                "log4j2/log4j2-test-automation.log");
    }

    public String getExecutedTests(){
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
        StringBuilder tests = new StringBuilder();
        tests.append("Report and Log added at ").append(date).append("\n\nExecuted Tests are: \n");
        for (String executedTest : executedTests) {
            tests.append(executedTest);
        }
        return tests.toString();
    }
}