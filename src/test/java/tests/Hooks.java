package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.HomePage;
import utilities.Driver;

import java.time.Duration;

public class Hooks {

    JavascriptExecutor js;
    HomePage page;

    @BeforeTest
    public void setup(){
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Driver.get().manage().deleteAllCookies();
        js = (JavascriptExecutor) Driver.get();
        page = new HomePage();
    }


    @AfterTest
    public void tearDown(){
        Driver.closeDriver();
    }
}
