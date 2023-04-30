package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

    /**
     * Used Singleton Design to avoid creating new instance for every call
     *      - Created private constructor to avoid instantiate
     *      - Created private static WebDriver object to call object directly using as ClassName.objectName
     *      - Checked driver value to avoid creating new instance if it has any value
     */

    private Driver(){}
    private static WebDriver driver;

    public static WebDriver get(){
        if(driver==null){
            String browser = ConfigReader.getInstance().get("browser");
            switch (browser) {
                case "chrome" -> {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setAcceptInsecureCerts(true);
                    options.addArguments("--disable-notifications");
                    driver = new ChromeDriver(options);
                }
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                case "safari" -> {
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                }
            }
        }
        return driver;
    }

    /**
     * After using selenium quit() method, we need to set driver as null.
     * Otherwise, when we call driver it won't create new instance
     */
    public static void closeDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }

}
