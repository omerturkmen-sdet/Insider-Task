package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.WebDriver.Driver;
import utils.logs.Log;

import java.time.Duration;

public class BaseTest {
    JavascriptExecutor js;
    HomePage page;

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
}